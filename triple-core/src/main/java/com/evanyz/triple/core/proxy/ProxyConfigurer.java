package com.evanyz.triple.core.proxy;

import com.evanyz.triple.core.config.BaseMaster;
import com.evanyz.triple.core.proxy.enums.ProxyType;

/**
 * Created by evan on 2019/1/6.
 */
public class ProxyConfigurer {

    private ProxyType proxyType = ProxyType.JDK;

    private BaseMaster baseMaster;

    public BaseMaster getBaseMaster() {
        return baseMaster;
    }

    public static ProxyConfigurer init() {
        return new ProxyConfigurer();
    }

    public ProxyConfigurer baseMaster(BaseMaster baseMaster) {
        this.baseMaster = baseMaster;
        return this;
    }

    public ProxyConfigurer proxyType(ProxyType proxyType) {
        this.proxyType = proxyType;
        return this;
    }

    public ProxyMaster build() {
        return new ProxyMaster(this);
    }
}
