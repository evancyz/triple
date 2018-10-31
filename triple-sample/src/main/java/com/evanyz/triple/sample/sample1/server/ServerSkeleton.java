package com.evanyz.triple.sample.sample1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evan on 2018/10/31.
 */
public class ServerSkeleton {

    public void init() {

        try {
            ServerSocket serverSocket = new ServerSocket(8889);

            //interrupt until receive msg
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String info = reader.readLine();
            System.out.println("接收到客户端的服务请求:" + info);
            socket.shutdownInput();

            String[] clientRequest = info.split("-");
            String className = clientRequest[0];
            String methodName = clientRequest[1];

            try {
                //invoke method
                Class clazz = Class.forName(className);
                Method method = clazz.getDeclaredMethod(methodName);
                String response = (String) method.invoke(clazz.newInstance());

                //send response to client
                OutputStream os = socket.getOutputStream();
                os.write(response.getBytes());
                os.flush();
                os.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
