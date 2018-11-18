package com.evanyz.triple.core.net;

import com.evanyz.triple.core.net.protocol.socket.server.SocketServer;

/**
 * Created by evan on 2018/11/18.
 */
public class ServerManager {

    private ServerManager(){}

    private static Server server = new SocketServer();

    public static Server getInstance() {
        return server;
    }
}
