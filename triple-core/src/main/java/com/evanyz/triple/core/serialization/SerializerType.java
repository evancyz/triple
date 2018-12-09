package com.evanyz.triple.core.serialization;

/**
 * Created by evan on 2018/12/9.
 */
public enum SerializerType {

    FAST_JSON(1, "fastJson"),

    ;


    int type;
    String desc;

    SerializerType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
