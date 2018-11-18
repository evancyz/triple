package com.evanyz.triple.core.serialization.impl;

import com.alibaba.fastjson.JSON;
import com.evanyz.triple.core.serialization.Serializer;

/**
 * Created by evan on 2018/11/11.
 */
public class FastJSONSerializer implements Serializer{

    @Override public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }
}
