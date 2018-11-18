package com.evanyz.triple.core.net.protocol.socket.server;

import com.evanyz.triple.core.net.Server;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.provider.ProviderFactory;
import com.evanyz.triple.core.serialization.Serializer;
import com.evanyz.triple.core.serialization.SerializerManager;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evan on 2018/11/12.
 */
public class SocketServer implements Server {

    @Override public void start() {

        try {

            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1", 9999));
            //register
            //RegisterCenterManager.getCenter().register(genServerAddress(serverSocket));
            //open socket
            while (true) {
                Socket socket = serverSocket.accept();

                try (BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())) {
                    //receive data
                    byte[] data = new byte[1024];
                    while (inputStream.read(data) != -1) {}
                    //get serializer
                    Serializer serializer = SerializerManager.getSerializer();

                    //deserialize
                    TripleRequest request = SerializerManager.getSerializer().deserialize(data, TripleRequest.class);

                    //invoke
                    TripleResponse response = ProviderFactory.newInstance().invoke(request);

                    //serialize
                    byte[] responseBytes = serializer.serialize(response);

                    try (OutputStream outputStream = socket.getOutputStream()) {
                        outputStream.write(responseBytes);
                        outputStream.flush();
                        socket.shutdownOutput();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("server socket error", e);
        }
    }

    private String genServerAddress(ServerSocket serverSocket) {
        return serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort();
    }

}
