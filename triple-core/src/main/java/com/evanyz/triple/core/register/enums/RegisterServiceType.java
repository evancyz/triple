package com.evanyz.triple.core.register.enums;

/**
 * Created by evan on 2019/1/6.
 */
public enum RegisterServiceType {


    ZOOKEEPER(1, "Zookeeper"),

    ;

    int type;
    String desc;

    RegisterServiceType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
