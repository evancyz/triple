package com.evanyz.triple.sample.sample2.client;

import com.evanyz.triple.core.proxy.ProxyManager;
import com.evanyz.triple.sample.sample2.api.HelloService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evan on 2018/11/13.
 */
public class Client {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

        for (int i =0;i<10;i++) {

            executorService.submit(()->{
                HelloService service = ProxyManager.getProxyFactory().getProxy(HelloService.class);
                System.out.println(service.hello());
            });

        }
    }
}
