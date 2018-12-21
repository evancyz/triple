package com.evanyz.triple.sample.sample2.client;

import com.evanyz.triple.core.proxy.ProxyManager;
import com.evanyz.triple.sample.sample2.api.HelloService;

/**
 * Created by evan on 2018/11/13.
 */
public class Client {

    public static void main(String[] args) {

        HelloService service = ProxyManager.getProxyFactory().getProxy(HelloService.class);
        System.out.println(service.hello());
    }
}
