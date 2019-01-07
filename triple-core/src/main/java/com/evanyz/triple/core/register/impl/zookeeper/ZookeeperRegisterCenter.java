package com.evanyz.triple.core.register.impl.zookeeper;

import com.evanyz.triple.core.config.BaseConfigurer;
import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.register.AbstractRegisterCenter;
import com.evanyz.triple.core.register.RegisterService;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;

/**
 * Created by evan on 2018/12/9.
 */
@Slf4j
public class ZookeeperRegisterCenter extends AbstractRegisterCenter {

    public static final String BASE_PATH = "tripleServices";

    private CuratorFramework createClient() {
        try {

            String zkServers = BaseConfigurer.getRegisterAddresses().stream().map(IpAndPort::toStr)
                .collect(Collectors.joining(","));

            CuratorFramework client = CuratorFrameworkFactory.newClient(zkServers, new RetryNTimes(10, 3000));

            client.start();
            return client;
        } catch (Exception e) {
            throw new RuntimeException("init zk client error", e);
        }
    }

    ServiceDiscovery createDiscovery(CuratorFramework client) {

        ServiceDiscovery<Void> discovery = ServiceDiscoveryBuilder.builder(Void.class)
            .basePath(BASE_PATH)
            .client(client)
            .build();

        try {
            discovery.start();
        } catch (Exception e) {
            throw new RuntimeException("init zk discovery client error", e);
        }

        return discovery;
    }


    @Override public RegisterService createService() {
        CuratorFramework client = createClient();

        ServiceDiscovery discovery = createDiscovery(client);

        return new ZookeeperRegisterService(client, discovery);
    }

}
