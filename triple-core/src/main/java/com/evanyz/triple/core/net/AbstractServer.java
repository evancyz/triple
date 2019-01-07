package com.evanyz.triple.core.net;

import com.evanyz.triple.core.net.handler.ListHandler;
import com.evanyz.triple.core.provider.ProviderMaster;

/**
 * Created by evan on 2018/12/9.
 */
public abstract class AbstractServer implements Server {

    ListHandler startHandler;

    ListHandler closeHandler;

    protected ProviderMaster master;

    public void setStartHandler(ListHandler startHandler) {
        this.startHandler = startHandler;
    }

    public void setCloseHandler(ListHandler closeHandler) {
        this.closeHandler = closeHandler;
    }

    public ListHandler getStartHandler() {
        return startHandler;
    }

    public ListHandler getCloseHandler() {
        return closeHandler;
    }

    @Override public void setMaster(ProviderMaster master) {
        this.master = master;
    }
}
