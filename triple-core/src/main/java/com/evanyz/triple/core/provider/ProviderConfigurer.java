package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.config.BaseMaster;
import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.provider.serviceDiscoverer.DefaultProviderServiceDiscoverer;
import com.evanyz.triple.core.provider.serviceDiscoverer.ProviderServiceDiscoverer;

/**
 * Created by evan on 2019/1/6.
 */
public class ProviderConfigurer {

    private String serviceScanPackage = "com.evanyz.triple.sample.sample2.server";

    private IpAndPort providerAddress = new IpAndPort("127.0.0.1", 9999);

    private BaseMaster baseMaster;

    private ProviderServiceDiscoverer serviceDiscoverer;

    public static ProviderConfigurer init() {
        return new ProviderConfigurer();
    }

    public ProviderConfigurer baseMaster(BaseMaster baseMaster) {
        this.baseMaster = baseMaster;
        return this;
    }

    public ProviderConfigurer serviceScanPackage(String serviceScanPackage) {
        this.serviceScanPackage = serviceScanPackage;
        return this;
    }

    public ProviderConfigurer providerAddress(String ip, int port) {
        this.providerAddress = new IpAndPort(ip, port);
        return this;
    }

    public ProviderConfigurer serviceDiscoverer(ProviderServiceDiscoverer discoverer) {
        this.serviceDiscoverer = discoverer;
        return this;
    }

    public ProviderMaster build() {
        if (serviceDiscoverer == null) {
            serviceDiscoverer = new DefaultProviderServiceDiscoverer();
        }
        return new ProviderMaster(this);
    }

    public ProviderServiceDiscoverer getServiceDiscoverer() {
        return serviceDiscoverer;
    }

    public BaseMaster getBaseMaster() {
        return baseMaster;
    }

    public IpAndPort getProviderAddress() {
        return providerAddress;
    }

    public String getServiceScanPackage() {
        return serviceScanPackage;
    }

}
