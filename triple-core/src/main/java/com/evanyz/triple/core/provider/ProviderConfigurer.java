package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.config.BaseMaster;
import com.evanyz.triple.core.domain.IpAndPort;

/**
 * Created by evan on 2019/1/6.
 */
public class ProviderConfigurer {

    public String serviceScanPackage = "com.evanyz.triple.sample.sample2.server";

    public IpAndPort providerAddress = new IpAndPort("127.0.0.1", 9999);

    private BaseMaster baseMaster;

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

    public ProviderMaster build() {
        return new ProviderMaster(this);
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
