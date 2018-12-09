package com.evanyz.triple.core.net.handler;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

/**
 * Created by evan on 2018/12/9.
 */
public class ListHandler extends AbstractHandler{

    public static ListHandler newHandler() {
        return new ListHandler();
    }

    public List<AbstractHandler> handlers = Lists.newArrayList();

    public ListHandler register(AbstractHandler handler) {
        handlers.add(handler);
        return this;
    }

    @Override public void process() {

        Iterator<AbstractHandler> iterator = handlers.iterator();

        while (iterator.hasNext()) {
            iterator.next().process();
        }
    }
}
