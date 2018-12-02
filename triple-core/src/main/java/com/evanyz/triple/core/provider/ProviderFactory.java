package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.config.ConfigManager;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.provider.annotation.TripleRpcService;
import com.evanyz.triple.core.utils.ClassUtils;
import com.evanyz.triple.core.utils.ReflectionUtils;
import com.evanyz.triple.core.utils.ServiceUtils;
import com.google.common.collect.Maps;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by evan on 2018/11/13.
 */
public class ProviderFactory {

    private static Map<String, Object> serviceFactory = Maps.newHashMap();

    private static ProviderFactory factory = new ProviderFactory();

    public static ProviderFactory getInstance() {
        return factory;
    }

    public static Object getService(String serviceName) {
        return serviceFactory.get(serviceName);
    }



    //registerService
    public static void register() {

        Set<Class<?>> classSets = ClassUtils.getClassSet(ConfigManager.getProviderBasePackageName());

        classSets.forEach(clazz -> {

            if (clazz.isAnnotationPresent(TripleRpcService.class)) {
                serviceFactory.put(ServiceUtils.genServiceName(clazz.getName()), ReflectionUtils.newInstance(clazz));
            }
        });
    }


    public TripleResponse invoke(TripleRequest tripleRequest) {

        Object service = getService(tripleRequest.getServiceName());

        for (Method method : service.getClass().getMethods()) {

            boolean satisfiedMethod = method.getName().equals(tripleRequest.getMethodName());
            boolean satisfiedParams = method.getParameters().length == Optional.ofNullable(tripleRequest.getParams()).map(e->e.length).orElse(0);

            if (satisfiedMethod && satisfiedParams) {
                Object o = ReflectionUtils.invokeMethod(service, method, tripleRequest.getParams());
                return TripleResponse.success(o);
            }
        }
        return TripleResponse.fail("失败");
    }
}
