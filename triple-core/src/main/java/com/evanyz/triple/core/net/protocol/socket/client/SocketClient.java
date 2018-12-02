package com.evanyz.triple.core.net.protocol.socket.client;

import com.evanyz.triple.core.net.ByteArrayReader;
import com.evanyz.triple.core.net.Client;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.serialization.Serializer;
import com.evanyz.triple.core.serialization.SerializerManager;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by evan on 2018/11/12.
 */
public class SocketClient implements Client {

    @Override public TripleResponse send(String address, TripleRequest request) {

        String[] addressAndPort= address.split(":");

        try (Socket socket = new Socket(addressAndPort[0], Integer.parseInt(addressAndPort[1]))) {

            //get serializer
            Serializer serializer = SerializerManager.getSerializer();

            //serialize
            byte[] data = ByteArrayReader.wrapData(serializer.serialize(request));

            //send data
            try (OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(data);
                outputStream.flush();
                socket.shutdownOutput();

                //receive data
                try (BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())) {
                    byte[] response = ByteArrayReader.read(inputStream);
                    //deserialize
                    return  serializer.deserialize(response, TripleResponse.class);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("client socket error", e);
        }
    }
}
