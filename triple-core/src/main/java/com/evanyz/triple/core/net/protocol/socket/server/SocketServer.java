package com.evanyz.triple.core.net.protocol.socket.server;

import com.evanyz.triple.core.net.AbstractServer;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.net.reader.StreamReader;
import com.evanyz.triple.core.serialization.Serializer;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evan on 2018/11/12.
 */
public class SocketServer extends AbstractServer {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public boolean flag = true;

    private ServerSocket serverSocket;



    @Override public void start() {

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(master.getProviderAddress().getIp(), master.getProviderAddress().getPort()));

            //开始扩展点
            getStartHandler().process();

            //open socket
            while (flag) {

                Socket socket = serverSocket.accept();

                executorService.execute(() -> {

                    try (BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())) {

                        StreamReader reader = master.getStreamReader();

                        //receive data
                        byte[] data = reader.getResponse(inputStream);

                        //get serializer
                        Serializer serializer = master.getSerializer();

                        //deserialize
                        TripleRequest request = serializer.deserialize(data, TripleRequest.class);

                        //invoke
                        TripleResponse response = master.getServiceFactory().invoke(request);

                        //serialize
                        byte[] responseBytes = reader.wrap(serializer.serialize(response));

                        try (OutputStream outputStream = socket.getOutputStream()) {
                            outputStream.write(responseBytes);
                            outputStream.flush();
                            socket.shutdownOutput();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("server socket error", e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("server socket error", e);
        }
    }

    @Override public void close() {
        try {
            //结束扩展点
            getCloseHandler().process();
            flag = false;
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
