package com.evanyz.triple.core.proxy;

import com.evanyz.triple.core.proxy.impl.jdk.JdkProxyFactory;

/**
 * Created by evan on 2018/11/13.
 */
public class ProxyManager {

    private static ProxyFactory proxyFactory = new JdkProxyFactory();

    public static ProxyFactory getProxyFactory() {
        return proxyFactory;
    }


}
