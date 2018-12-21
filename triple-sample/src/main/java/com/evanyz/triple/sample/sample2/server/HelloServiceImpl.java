package com.evanyz.triple.sample.sample2.server;

import com.evanyz.triple.core.provider.annotation.TripleRpcService;
import com.evanyz.triple.sample.sample2.api.HelloService;

/**
 * Created by evan on 2018/11/19.
 */
@TripleRpcService
public class HelloServiceImpl implements HelloService{

    @Override public String hello() {
        return "哈哈哈哈";
    }
}
