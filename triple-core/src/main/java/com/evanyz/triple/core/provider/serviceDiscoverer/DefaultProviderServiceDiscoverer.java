package com.evanyz.triple.core.provider.serviceDiscoverer;

import com.evanyz.triple.core.utils.ReflectionUtils;

/**
 * Created by evan on 2019/1/19.
 */
public class DefaultProviderServiceDiscoverer extends ProviderServiceDiscoverer{

    @Override public Object discovery(String serviceName, Class clazz) {
        return ReflectionUtils.newInstance(clazz);
    }
}
