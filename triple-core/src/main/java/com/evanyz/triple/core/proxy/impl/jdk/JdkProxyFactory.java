package com.evanyz.triple.core.proxy.impl.jdk;

import com.evanyz.triple.core.proxy.ProxyFactory;
import java.lang.reflect.Proxy;

/**
 * Created by evan on 2018/11/11.
 */
public class JdkProxyFactory<T> implements ProxyFactory<T> {
    @Override public T getProxy(Class<T> clazz) {
        try {
            return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[] {clazz},
                (proxy, method, args) -> {
                    return null;
                });
        }catch (Exception e){

            throw new RuntimeException("生成Java代理类失败 ", e);
        }
    }
}
