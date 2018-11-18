package com.evanyz.triple.core.net;

import com.evanyz.triple.core.net.protocol.socket.client.SocketClient;

/**
 * Created by evan on 2018/11/13.
 */
public class ClientManager {

    private ClientManager() {}

    private static Client client = new SocketClient();

    public static Client getInstance() {
        return client;
    }
}
