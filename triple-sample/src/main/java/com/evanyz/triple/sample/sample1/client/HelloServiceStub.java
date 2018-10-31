package com.evanyz.triple.sample.sample1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by evan on 2018/10/31.
 *
 * 存根
 * 主要是代理原接口类
 * 进行网络连接
 */
public class HelloServiceStub implements HelloService{

    @Override public String sayHello() {

        //通信使用最基本的Socket

        try {
            Socket socket = new Socket("127.0.0.1", 8889);
            OutputStream os = socket.getOutputStream();

            String service = "com.evanyz.triple.sample.sample1.server.HelloService-sayHello\n";
            os.write(service.getBytes());
            os.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            reader.close();
            os.close();
            socket.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
