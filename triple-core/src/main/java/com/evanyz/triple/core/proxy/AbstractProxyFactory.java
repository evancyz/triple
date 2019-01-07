package com.evanyz.triple.core.proxy;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.net.Client;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.register.RegisterService;
import java.util.List;

/**
 * Created by evan on 2019/1/7.
 */
public abstract class AbstractProxyFactory implements ProxyFactory {

    private ProxyMaster master;

    public TripleResponse handler(TripleRequest tripleRequest) {
        RegisterService registerService = master.getRegisterService();
        List<IpAndPort> discover = registerService.discover(tripleRequest.getServiceName());
        Client client = master.getClient();
        return client.send(discover.iterator().next(), tripleRequest);
    }

    @Override public void setMaster(ProxyMaster master) {
        this.master = master;
    }
}
