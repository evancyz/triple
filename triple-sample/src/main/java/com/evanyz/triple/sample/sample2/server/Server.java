package com.evanyz.triple.sample.sample2.server;

import com.evanyz.triple.core.provider.annotation.ProviderConfigurer;

/**
 * Created by evan on 2018/11/18.
 */
public class Server {

    public static void main(String[] args) {
        ProviderConfigurer
            .init()
            .buildMaster()
            .haveFun();

    }
}
