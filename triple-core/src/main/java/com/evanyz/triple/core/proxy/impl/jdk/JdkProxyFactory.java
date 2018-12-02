package com.evanyz.triple.core.proxy.impl.jdk;

import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.proxy.ProxyFactory;
import com.evanyz.triple.core.proxy.impl.ProxyHandler;
import com.evanyz.triple.core.utils.ServiceUtils;
import java.lang.reflect.Proxy;

/**
 * Created by evan on 2018/11/11.
 */
public class JdkProxyFactory implements ProxyFactory {
    @Override public <T> T getProxy(Class<T> clazz) {
        try {
            return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[] {clazz},
                (proxy, method, args) -> {
                    TripleRequest request = new TripleRequest();
                    request.setServiceName(ServiceUtils.genServiceName(clazz.getName()));
                    request.setMethodName(method.getName());
                    request.setParams(args);
                    return ProxyHandler.handler(request).getData();
                });
        }catch (Exception e){

            throw new RuntimeException("生成Java代理类失败 ", e);
        }
    }
}
