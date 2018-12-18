package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.provider.annotation.TripleRpcService;
import com.evanyz.triple.core.utils.ClassUtils;
import com.evanyz.triple.core.utils.ReflectionUtils;
import com.evanyz.triple.core.utils.ServiceUtils;
import com.google.common.collect.Maps;
import java.io.Closeable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by evan on 2018/11/13.
 */
public class ProviderServiceFactory implements Closeable, ProviderMasterAware {


    private static Map<String, Object> serviceFactory = Maps.newHashMap();

    private static ProviderServiceFactory factory = new ProviderServiceFactory();

    private ProviderMaster master;

    public static ProviderServiceFactory getInstance() {
        return factory;
    }

    public void setMaster(ProviderMaster master) {
        this.master = master;
    }

    public static Object getService(String serviceName) {
        return serviceFactory.get(serviceName);
    }

    //registerService
    public void registerService() {

        Set<Class<?>> classSets = ClassUtils.getClassSet(master.getBasePackage());

        classSets.forEach(clazz -> {

            if (clazz.isAnnotationPresent(TripleRpcService.class)) {
                //注册中心注册服务
                master.getRegisterService().register(master.getIpAndPort(), clazz.getName());
                //Factory注册服务实例
                serviceFactory.put(ServiceUtils.genServiceName(clazz.getName()), ReflectionUtils.newInstance(clazz));
            }
        });
    }

    @Override
    public void close() {
        //removeService
        serviceFactory.keySet().forEach(serviceName ->
            master.getRegisterService().unRegister(master.getIpAndPort(), serviceName)
        );
    }

    public TripleResponse invoke(TripleRequest tripleRequest) {

        Object service = getService(tripleRequest.getServiceName());

        for (Method method : service.getClass().getMethods()) {

            boolean satisfiedMethod = method.getName().equals(tripleRequest.getMethodName());
            boolean satisfiedParams = method.getParameters().length == Optional.ofNullable(tripleRequest.getParams()).map(e -> e.length).orElse(0);

            if (satisfiedMethod && satisfiedParams) {
                Object o = ReflectionUtils.invokeMethod(service, method, tripleRequest.getParams());
                return TripleResponse.success(o);
            }
        }
        return TripleResponse.fail("失败");
    }

    @Override public void setProviderMaster(ProviderMaster master) {
        this.master = master;
    }
}
