package com.evanyz.triple.core.proxy;

/**
 * Created by evan on 2018/11/13.
 */
public class ProxyManager {

    public static <T> T getProxy(Class<T> clazz) {
        return ProxyMaster.getProxyFactory().getProxy(clazz);
    }
}
