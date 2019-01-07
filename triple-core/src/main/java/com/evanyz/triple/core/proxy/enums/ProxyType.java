package com.evanyz.triple.core.proxy.enums;

/**
 * Created by evan on 2019/1/6.
 */
public enum ProxyType {

    JDK(1, "Jdk Proxy"),
    CGLIB(2, "Cglib Proxy"),
    ;
    int type;
    String desc;

    ProxyType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
