package com.evanyz.triple.sample.sample1.client;

/**
 * Created by evan on 2018/10/31.
 */
public class Client {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceStub();
        System.out.println("调用远程Server服务，返回：" + helloService.sayHello());

    }
}
