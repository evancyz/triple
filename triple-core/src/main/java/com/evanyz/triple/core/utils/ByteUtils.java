package com.evanyz.triple.core.utils;

/**
 * Created by evan on 2018/11/26.
 */
public class ByteUtils {

    public static byte[] int2byte(int res) {
        byte[] targets = new byte[3];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        return targets;
    }

    public static int byte2int(byte[] res) {
        return (res[0] & 0xff) |
            ((res[1] << 8) & 0xff00) |
            ((res[2] << 24) >>> 8);
    }

}
