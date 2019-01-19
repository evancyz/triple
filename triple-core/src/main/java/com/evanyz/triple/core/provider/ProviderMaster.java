package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.net.AbstractServer;
import com.evanyz.triple.core.net.handler.AbstractHandler;
import com.evanyz.triple.core.net.handler.ListHandler;
import com.evanyz.triple.core.net.protocol.socket.server.SocketServer;
import com.evanyz.triple.core.net.reader.StreamReader;
import com.evanyz.triple.core.register.RegisterService;
import com.evanyz.triple.core.serialization.Serializer;
import java.io.Closeable;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by evan on 2018/12/9.
 */
@Slf4j
public class ProviderMaster implements Closeable {

    private ProviderMaster() {}

    private ProviderConfigurer configurer;

    private AbstractServer server;

    private ProviderServiceFactory serviceFactory;

    public ProviderMaster(ProviderConfigurer configurer) {

        this.configurer = configurer;

        //init service factory
        serviceFactory = new ProviderServiceFactory();
        serviceFactory.setMaster(this);
        //register service
        serviceFactory.registerService();

        //init server
        server = new SocketServer();
        server.setMaster(this);

        //register server handler
        server.setStartHandler(ListHandler.newHandler().register(new AbstractHandler() {
            @Override
            public void process() {
            }
        }));
        server.setCloseHandler(ListHandler.newHandler().register(new AbstractHandler() {
            @Override
            public void process() {
            }
        }));
    }

    public void start() {
        log.info(">>>>> Triple RPC Server start success");
        this.server.start();
    }

    @Override
    public void close() {
        serviceFactory.close();
        server.close();
    }

    public Serializer getSerializer() {
        return this.configurer.getBaseMaster().getSerializer();
    }

    public  RegisterService getRegisterService() {
        return this.configurer.getBaseMaster().getRegisterService();
    }

    public StreamReader getStreamReader() {
        return this.configurer.getBaseMaster().getStreamReader();
    }

    public String getServiceScanPackage() {
        return this.configurer.getServiceScanPackage();
    }

    public IpAndPort getProviderAddress() {
        return this.configurer.getProviderAddress();
    }

    public ProviderServiceFactory getServiceFactory() {
        return serviceFactory;
    }

}
