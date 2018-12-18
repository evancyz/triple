package com.evanyz.triple.core.utils;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by evan on 2018/12/9.
 */
public class IpUtils {

    public static String genIpAddress(String ip, int port) {
        return ip + ":" + port;
    }

    public static Pair<String, Integer> getIpPort(String ipPort) {

        String[] data = ipPort.split(":");

        return Pair.of(data[0], Integer.parseInt(data[1]));

    }
}
