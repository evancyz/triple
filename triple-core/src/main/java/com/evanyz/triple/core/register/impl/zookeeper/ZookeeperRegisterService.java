package com.evanyz.triple.core.register.impl.zookeeper;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.register.RegisterService;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Created by evan on 2018/12/9.
 */
@Slf4j
public class ZookeeperRegisterService implements RegisterService {


    private CuratorFramework client;

    private ServiceDiscovery discovery;

    public ZookeeperRegisterService(CuratorFramework client, ServiceDiscovery discovery) {
        this.client = client;
        this.discovery = discovery;
    }

    @Override public void register(IpAndPort ipAndPort, String serviceName) {

        try {
            ServiceInstance serviceInstance = ServiceInstance.builder()
                .name(serviceName)
                .address(ipAndPort.getIp())
                .port(ipAndPort.getPort())
                .build();

            discovery.registerService(serviceInstance);
        } catch (Exception e) {
            log.error("triple provider register fail {}", e);
        }
    }

    @Override public void unRegister(IpAndPort ipAndPort, String serviceName) {

        try {
            ServiceInstance serviceInstance = ServiceInstance.builder()
                .name(serviceName)
                .address(ipAndPort.getIp())
                .port(ipAndPort.getPort())
                .build();

            discovery.unregisterService(serviceInstance);
        } catch (Exception e) {
            log.error("triple provider register fail {}", e);
        }
    }

    @Override public List<IpAndPort> discover(String serviceName) {
        try {
            Collection<ServiceInstance> instances = discovery.queryForInstances(serviceName);
            return instances.stream().map(e -> new IpAndPort(e.getAddress(), e.getPort())).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("triple discover fail {}", e);
            return Lists.newArrayList();
        }
    }
}
