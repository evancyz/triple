package com.evanyz.triple.core.serialization.impl;

import com.evanyz.triple.core.serialization.Serializer;

/**
 * Created by evan on 2018/11/11.
 */
public class JSONSerializer implements Serializer{

    @Override public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override public Object deserialize(byte[] data, Class clazz) {
        return null;
    }
}
