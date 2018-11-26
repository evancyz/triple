package com.evanyz.triple.sample.sample2.server;

import com.evanyz.triple.core.provider.annotation.TripleRpcService;

/**
 * Created by evan on 2018/11/19.
 */
@TripleRpcService
public class HelloServiceImpl {

    public String hello() {
        return "hello world!";
    }
}
