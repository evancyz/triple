package com.evanyz.triple.core.serialization;

/**
 * Created by evan on 2018/11/11.
 */
public interface Serializer {

    //序列化,反序列化
    byte[] serialize(Object object);

    <T> T deserialize(byte[] data, Class<T> clazz);
}
