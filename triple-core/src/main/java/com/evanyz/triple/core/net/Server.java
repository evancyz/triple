package com.evanyz.triple.core.net;

import com.evanyz.triple.core.provider.ProviderMasterAware;

/**
 * Created by evan on 2018/11/11.
 */
public interface Server extends ProviderMasterAware{

    void start();

    void close();

}
