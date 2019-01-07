package com.evanyz.triple.sample.sample2.server;

import com.evanyz.triple.core.config.BaseConfigurer;
import com.evanyz.triple.core.config.BaseMaster;
import com.evanyz.triple.core.provider.ProviderConfigurer;

/**
 * Created by evan on 2018/11/18.
 */
public class Server {

    public static void main(String[] args) {

        BaseMaster baseMaster = BaseConfigurer.init().build();

        ProviderConfigurer.init().baseMaster(baseMaster).build().start();
    }
}
