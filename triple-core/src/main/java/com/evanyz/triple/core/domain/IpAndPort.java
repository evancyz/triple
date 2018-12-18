package com.evanyz.triple.core.domain;

import lombok.AllArgsConstructor;

/**
 * Created by evan on 2018/12/18.
 */
@AllArgsConstructor
public class IpAndPort {
    String ip;
    int port;

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
