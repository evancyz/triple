package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.provider.annotation.TripleRpcService;
import com.evanyz.triple.core.provider.serviceDiscoverer.ProviderServiceDiscoverer;
import com.evanyz.triple.core.register.RegisterService;
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
 * Created by evan on 2019/1/19.
 */
public class ProviderServiceFactory implements ProviderMasterAware, Closeable {

    private static Map<String, Object> serviceFactory = Maps.newHashMap();

    private ProviderMaster master;

    private RegisterService registerService;

    private ProviderServiceDiscoverer discovery;

    public static Object getService(String serviceName) {
        return serviceFactory.get(serviceName);
    }

    //registerService
    public void registerService() {

        Set<Class<?>> classSets = ClassUtils.getClassSet(master.getServiceScanPackage());

        classSets.forEach(clazz -> {
            if (clazz.isAnnotationPresent(TripleRpcService.class)) {
                //TODO 简单先实现一下
                String serviceName = ServiceUtils.genServiceName(clazz.getInterfaces()[0].getName());
                //注册中心注册服务
                registerService.register(master.getProviderAddress(), serviceName);
                //Factory注册服务实例
                serviceFactory.put(serviceName, discovery.discovery(serviceName, clazz));
            }
        });
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

    @Override
    public void close() {
        //removeService
        serviceFactory.keySet().forEach(serviceName ->
            registerService.unRegister(master.getProviderAddress(), serviceName)
        );

    }

    @Override public void setMaster(ProviderMaster master) {
        this.master = master;
        this.registerService = master.getRegisterService();
    }


}
