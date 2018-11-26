package com.evanyz.triple.core.provider;

import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Created by evan on 2018/11/13.
 */
public class ProviderFactory {

    public static Map<String, Object> serviceFactory = Maps.newHashMap();

    private static ProviderFactory factory = new ProviderFactory();

    public static ProviderFactory newInstance() {
        return factory;
    }



    public TripleResponse invoke(TripleRequest tripleRequest) {
        TripleResponse response = new TripleResponse();
        response.setData("hello world!");
        return response;
    }
}
