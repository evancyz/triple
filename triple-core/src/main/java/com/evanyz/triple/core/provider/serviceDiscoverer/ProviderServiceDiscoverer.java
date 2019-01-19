package com.evanyz.triple.core.provider.serviceDiscoverer;

/**
 * Created by evan on 2019/1/19.
 */
public abstract class ProviderServiceDiscoverer {

    public abstract Object discovery(String serviceName, Class clazz);

}
