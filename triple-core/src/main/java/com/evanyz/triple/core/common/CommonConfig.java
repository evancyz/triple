package com.evanyz.triple.core.common;

/**
 * Created by evan on 2018/12/9.
 */
public class CommonConfig {

    private static String zkServers = "127.0.0.1:2181";

    public static String getZkServers() {
        return zkServers;
    }
}
