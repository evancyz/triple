package com.evanyz.triple.sample.sample2.client;

import com.evanyz.triple.core.config.BaseConfigurer;
import com.evanyz.triple.core.config.BaseMaster;
import com.evanyz.triple.core.proxy.ProxyConfigurer;
import com.evanyz.triple.core.proxy.ProxyManager;
import com.evanyz.triple.sample.sample2.api.HelloService;

/**
 * Created by evan on 2018/11/13.
 */
public class Client {

    public static void main(String[] args) {

        BaseMaster baseMaster = BaseConfigurer.init().build();

        ProxyConfigurer.init().baseMaster(baseMaster).build().start();

        System.out.println(ProxyManager.getProxy(HelloService.class).hello());



    }
}
