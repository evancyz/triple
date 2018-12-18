package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.net.AbstractServer;
import com.evanyz.triple.core.net.handler.AbstractHandler;
import com.evanyz.triple.core.net.handler.ListHandler;
import com.evanyz.triple.core.net.protocol.socket.server.SocketServer;
import com.evanyz.triple.core.provider.annotation.ProviderConfigurer;
import com.evanyz.triple.core.register.RegisterService;
import com.evanyz.triple.core.register.impl.zookeeper.ZookeeperRegisterCenter;
import com.evanyz.triple.core.serialization.Serializer;
import com.evanyz.triple.core.serialization.impl.FastJSONSerializer;
import com.sun.xml.internal.ws.Closeable;

/**
 * Created by evan on 2018/12/9.
 */
public class ProviderMaster implements Closeable {

    private ProviderMaster() {
    }

    public ProviderMaster(ProviderConfigurer config) {
        this.configurer = config;
    }

    private ProviderConfigurer configurer;

    private AbstractServer server;

    private Serializer serializer;

    private ProviderServiceFactory providerServiceFactory;

    private RegisterService registerService;


    public void haveFun() {

        //TODO use config
        //init serializer
        serializer = new FastJSONSerializer();
        //init registerService
        registerService = new ZookeeperRegisterCenter().createService();
        //init providerFactory
        providerServiceFactory = ProviderServiceFactory.getInstance();
        providerServiceFactory.setMaster(this);
        //register service
        providerServiceFactory.registerService();
        //init server
        server = new SocketServer();
        server.setProviderMaster(this);

        //register server handler
        server.setStartHandler(ListHandler.newHandler().register(new AbstractHandler() {
            @Override public void process() {
            }
        }));
        server.setCloseHandler(ListHandler.newHandler().register(new AbstractHandler() {
            @Override public void process() {
            }
        }));

        //server start
        server.start();
    }

    @Override public void close() {
        providerServiceFactory.close();
        server.close();
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public String getIp() {
        return this.configurer.getIp();
    }

    public int getPort() {
        return this.configurer.getPort();
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public String getBasePackage() {
        return this.configurer.getProviderBasePackageName();
    }

    public IpAndPort getIpAndPort() {
        return new IpAndPort(getIp(), getPort());
    }



}
