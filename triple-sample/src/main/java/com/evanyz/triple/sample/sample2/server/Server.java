package com.evanyz.triple.sample.sample2.server;

import com.evanyz.triple.core.net.ServerManager;

/**
 * Created by evan on 2018/11/18.
 */
public class Server {

    public static void main(String[] args) {
        ServerManager.getInstance().start();
    }
}
