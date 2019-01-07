package com.evanyz.triple.core.net.enums;

/**
 * Created by evan on 2018/12/9.
 */
public enum NetType {

    SOCKET_SHORT_CONNECTION(1, "socket短连接"),
    ;

    int type;
    String desc;

    NetType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
