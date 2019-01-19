package com.evanyz.triple.core.config;

import com.evanyz.triple.core.net.reader.StreamReader;
import com.evanyz.triple.core.net.reader.impl.TripleReader;
import com.evanyz.triple.core.register.RegisterService;
import com.evanyz.triple.core.register.impl.zookeeper.ZookeeperRegisterCenter;
import com.evanyz.triple.core.serialization.Serializer;
import com.evanyz.triple.core.serialization.impl.FastJSONSerializer;

/**
 * Created by evan on 2019/1/6.
 */
public class BaseMaster {

    private BaseMaster() {}

    private Serializer serializer;

    private StreamReader streamReader;

    private RegisterService registerService;

    public BaseMaster(BaseConfigurer baseConfigurer) {
        //TODO use baseConfigurer to init
        serializer = new FastJSONSerializer();
        streamReader = new TripleReader();
        registerService = new ZookeeperRegisterCenter().getService();
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public StreamReader getStreamReader() {
        return streamReader;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }


}
