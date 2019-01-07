package com.evanyz.triple.core.net.protocol.socket.client;

import com.evanyz.triple.core.domain.IpAndPort;
import com.evanyz.triple.core.net.Client;
import com.evanyz.triple.core.net.domain.TripleRequest;
import com.evanyz.triple.core.net.domain.TripleResponse;
import com.evanyz.triple.core.net.reader.StreamReader;
import com.evanyz.triple.core.proxy.ProxyMaster;
import com.evanyz.triple.core.serialization.Serializer;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by evan on 2018/11/12.
 */
public class SocketClient implements Client{

    private ProxyMaster master;

    @Override public TripleResponse send(IpAndPort ipAndPort, TripleRequest request) {

        try (Socket socket = new Socket(ipAndPort.getIp(), ipAndPort.getPort())) {

            //get serializer
            Serializer serializer = master.getSerializer();

            //get Reader
            StreamReader reader =  master.getStreamReader();

            //serialize
            byte[] data = reader.wrap(serializer.serialize(request));

            //send data
            try (OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(data);
                outputStream.flush();
                socket.shutdownOutput();

                //receive data
                try (BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())) {
                    byte[] response = reader.getResponse(inputStream);
                    //deserialize
                    return serializer.deserialize(response, TripleResponse.class);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("client socket error", e);
        }
    }

    @Override public void setMaster(ProxyMaster master) {
        this.master = master;
    }
}
