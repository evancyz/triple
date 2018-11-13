package com.evanyz.triple.core.proxy;

/**
 * Created by evan on 2018/11/11.
 */
public interface ProxyFactory {

    <T> T getProxy(Class<T> clazz);
}
