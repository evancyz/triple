package com.evanyz.triple.core.register;

/**
 * Created by evan on 2018/12/9.
 */
public abstract class AbstractRegisterCenter implements RegisterCenter{

    private RegisterService registerService;

    @Override
    public RegisterService getService() {

        if (registerService == null) {
            registerService = createService();
        }
        return registerService;
    }

    public abstract RegisterService createService();
}
