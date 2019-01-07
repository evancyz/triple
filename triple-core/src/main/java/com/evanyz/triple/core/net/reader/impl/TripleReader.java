package com.evanyz.triple.core.net.reader.impl;

import com.evanyz.triple.core.net.reader.StreamReader;
import com.evanyz.triple.core.utils.ByteUtils;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by evan on 2018/11/27.
 */
public class TripleReader implements StreamReader{

    @Override
    public byte[] wrap(byte[] data) {
        byte[] size = ByteUtils.int2byte(data.length);
        byte[] newDate = new byte[data.length + size.length];
        System.arraycopy(size, 0, newDate, 0, 3);
        System.arraycopy(data, 0, newDate, 3, data.length);
        return newDate;
    }

    @Override
    public byte[] getResponse(InputStream inputStream) {
        try {
            byte[] size = new byte[3];
            inputStream.read(size);
            int dataSize = ByteUtils.byte2int(size);
            byte[] dataBytes = new byte[dataSize];
            while (inputStream.read(dataBytes) != -1) {
            }
            return dataBytes;
        } catch (IOException e) {
            throw new RuntimeException("read error", e);
        }
    }
}
