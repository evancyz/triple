package com.evanyz.triple.core.serialization;

import com.evanyz.triple.core.serialization.impl.FastJSONSerializer;

/**
 * Created by evan on 2018/11/12.
 */
public class SerializerManager {

    private SerializerManager() {
    }

    private static Serializer serializer = new FastJSONSerializer();

    public static Serializer getSerializer() {
        return serializer;
    }



}
