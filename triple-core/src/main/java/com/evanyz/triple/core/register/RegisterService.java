package com.evanyz.triple.core.register;

import com.evanyz.triple.core.domain.IpAndPort;
import java.util.List;

/**
 * Created by evan on 2018/12/9.
 */
public interface RegisterService {

    void register(IpAndPort ipAndPort, String serviceName);

    void unRegister(IpAndPort ipAndPort, String serviceName);

    List<IpAndPort> discover(String serviceName);
}
