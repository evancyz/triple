package com.evanyz.triple.core.register;

import java.util.List;

/**
 * Created by evan on 2018/11/11.
 */
public interface RegisterCenter {

    void register(String provider);

    List<String> getProvider();
}
