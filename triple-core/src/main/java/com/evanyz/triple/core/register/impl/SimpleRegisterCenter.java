package com.evanyz.triple.core.register.impl;

import com.evanyz.triple.core.register.RegisterCenter;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by evan on 2018/11/11.
 */
public class SimpleRegisterCenter implements RegisterCenter {

    List<String> providers = Lists.newArrayList();

    @Override public void register(String provider) {
        providers.add(provider);
    }

    @Override public List<String> getProvider() {
        return providers;
    }
}
