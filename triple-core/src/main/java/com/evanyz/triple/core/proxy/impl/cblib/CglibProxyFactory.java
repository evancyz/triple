package com.evanyz.triple.core.proxy.impl.cblib;

import com.evanyz.triple.core.proxy.ProxyFactory;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by evan on 2018/11/11.
 */
public class CglibProxyFactory<T> implements ProxyFactory<T> {

    @Override public T getProxy(Class<T> clazz) {

        try {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(new MyMethodInterceptor(clazz.newInstance()));
            return (T)enhancer.create();
        } catch (Exception e) {
            throw new RuntimeException("生成cglib代理失败", e);
        }
    }
}
