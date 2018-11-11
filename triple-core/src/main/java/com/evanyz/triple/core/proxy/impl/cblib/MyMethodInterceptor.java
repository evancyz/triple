package com.evanyz.triple.core.proxy.impl.cblib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by evan on 2018/11/11.
 */
public class MyMethodInterceptor implements MethodInterceptor{

    private Object target;

    public MyMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return method.invoke(o, objects);
    }
}
