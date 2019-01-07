package com.evanyz.triple.core.proxy;

import com.evanyz.triple.core.net.Client;
import com.evanyz.triple.core.net.protocol.socket.client.SocketClient;
import com.evanyz.triple.core.net.reader.StreamReader;
import com.evanyz.triple.core.proxy.impl.jdk.JdkProxyFactory;
import com.evanyz.triple.core.register.RegisterService;
import com.evanyz.triple.core.serialization.Serializer;

/**
 * Created by evan on 2019/1/6.
 */
public class ProxyMaster {

    private static ProxyFactory proxyFactory;

    private Client client;

    private ProxyConfigurer configurer;

    private ProxyMaster() {}

    public ProxyMaster(ProxyConfigurer configurer) {
        this.configurer = configurer;

        //use ProxyConfigurer to init
        client = new SocketClient();
        client.setMaster(this);

        proxyFactory = new JdkProxyFactory();
        proxyFactory.setMaster(this);
    }

    public void start() {
        //todo
    }

    public Serializer getSerializer() {
        return this.configurer.getBaseMaster().getSerializer();
    }

    public RegisterService getRegisterService() {
        return this.configurer.getBaseMaster().getRegisterService();
    }

    public StreamReader getStreamReader() {
        return this.configurer.getBaseMaster().getStreamReader();
    }

    public Client getClient() {
        return client;
    }

    public static ProxyFactory getProxyFactory() {
        return proxyFactory;
    }
}
