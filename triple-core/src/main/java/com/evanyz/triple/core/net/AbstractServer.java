package com.evanyz.triple.core.net;

import com.evanyz.triple.core.net.handler.ListHandler;

/**
 * Created by evan on 2018/12/9.
 */
public abstract class AbstractServer implements Server{

    ListHandler startHandler;

    ListHandler endHandler;

    public void setStartHandler(ListHandler startHandler) {
        this.startHandler = startHandler;
    }

    public void setEndHandler(ListHandler endHandler) {
        this.endHandler = endHandler;
    }

    public ListHandler getStartHandler() {
        return startHandler;
    }

    public ListHandler getEndHandler() {
        return endHandler;
    }
}
