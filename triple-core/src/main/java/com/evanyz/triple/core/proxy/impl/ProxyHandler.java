package com.evanyz.triple.core.proxy.impl;

import com.evanyz.triple.core.net.Client;
import com.evanyz.triple.core.net.ClientManager;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import java.util.List;

/**
 * Created by evan on 2018/11/13.
 */
public class ProxyHandler {

    public static TripleResponse handler(TripleRequest tripleRequest) {
        //TODO
        List<String> provider = null;
        Client client = ClientManager.getInstance();
        return client.send(provider.iterator().next(), tripleRequest);
    }
}

