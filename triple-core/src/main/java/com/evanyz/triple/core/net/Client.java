package com.evanyz.triple.core.net;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.proxy.ProxyMasterAware;

/**
 * Created by evan on 2018/11/11.
 */
public interface Client extends ProxyMasterAware{

    TripleResponse send(IpAndPort ipAndPort, TripleRequest request);
}
