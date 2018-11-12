package com.evanyz.triple.core.register;

import com.evanyz.triple.core.register.impl.SimpleRegisterCenter;

/**
 * Created by evan on 2018/11/12.
 */
public class RegisterCenterManager {

    private RegisterCenterManager(){
    }

    private static RegisterCenter registerCenter = new SimpleRegisterCenter();

    public static RegisterCenter getCenter() {
        return registerCenter;
    }
}
