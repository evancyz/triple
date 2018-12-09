package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.net.AbstractServer;
import com.evanyz.triple.core.net.handler.AbstractHandler;
import com.evanyz.triple.core.net.handler.ListHandler;
import com.evanyz.triple.core.net.protocol.socket.server.SocketServer;
import com.evanyz.triple.core.provider.annotation.ProviderConfig;
import com.evanyz.triple.core.register.RegisterCenterManager;
import com.evanyz.triple.core.serialization.Serializer;
import com.evanyz.triple.core.serialization.impl.FastJSONSerializer;
import com.evanyz.triple.core.utils.IpUtils;

/**
 * Created by evan on 2018/12/9.
 */
public class ProviderMaster {

    private ProviderMaster() {
    }

    public ProviderMaster(ProviderConfig config) {
        this.providerConfig = config;
    }

    private ProviderConfig providerConfig;

    private AbstractServer server;

    private Serializer serializer;

    public void haveFun() {

        //TODO use config
        serializer = new FastJSONSerializer();

        //register server
        ProviderServiceFactory.getInstance().registerService(providerConfig.getProviderBasePackageName());

        //开启Server
        server = new SocketServer();

        //往注册中心打服务
        server.setStartHandler(ListHandler.newHandler().register(new AbstractHandler() {
            @Override public void process() {
                RegisterCenterManager.getCenter().register(IpUtils.genIpAddress(getIp(), getPort()));
            }
        }));

        server.start(this);
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public String getIp() {
        return this.providerConfig.getIp();
    }

    public int getPort() {
        return this.providerConfig.getPort();
    }

}
