package com.evanyz.triple.core.config;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.net.enums.NetType;
import com.evanyz.triple.core.net.enums.StreamReaderType;
import com.evanyz.triple.core.register.enums.RegisterServiceType;
import com.evanyz.triple.core.serialization.SerializerType;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by evan on 2019/1/6.
 */
public class BaseConfigurer {

    //网络协议
    private static NetType netType = NetType.SOCKET_SHORT_CONNECTION;

    //序列化方式
    private static SerializerType serializerType = SerializerType.FAST_JSON;

    //流处理方式
    private static StreamReaderType streamReaderType = StreamReaderType.TRIPLE_DEFAULT_TYPE;

    //注册中心
    private static RegisterServiceType registerServiceType = RegisterServiceType.ZOOKEEPER;

    //注册中心地址
    private static List<IpAndPort> registerAddresses = Lists.newArrayList(new IpAndPort("127.0.0.1", 2181));

    public static List<IpAndPort> getRegisterAddresses() {
        return registerAddresses;
    }

    public static BaseConfigurer init() {
        return new BaseConfigurer();
    }

    public BaseMaster build() {
        return new BaseMaster(this);
    }

}
