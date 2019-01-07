package com.evanyz.triple.core.net.reader;

import java.io.InputStream;

/**
 * Created by evan on 2019/1/6.
 */
public interface StreamReader {

    byte[] wrap(byte[] data);

    byte[] getResponse(InputStream inputStream);

}
