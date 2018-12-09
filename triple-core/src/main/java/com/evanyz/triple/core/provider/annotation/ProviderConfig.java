package com.evanyz.triple.core.provider.annotation;

import com.evanyz.triple.core.net.NetType;
import com.evanyz.triple.core.provider.ProviderMaster;
import com.evanyz.triple.core.serialization.SerializerType;
import lombok.Getter;

/**
 * Created by evan on 2018/12/9.
 */
@Getter
public class ProviderConfig {

    public static ProviderConfig init() {
        return new ProviderConfig();
    }

    NetType netType = NetType.SOCKET_SHORT_CONNECTION;

    SerializerType serializerType = SerializerType.FAST_JSON;

    private String providerBasePackageName = "com.evanyz.triple.sample.sample2.server";

    private String ip = "127.0.0.1";

    int port = 9999;

    public ProviderMaster funCenter() {
        return new ProviderMaster(this);
    }
}
