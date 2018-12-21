package com.evanyz.triple.core.utils;

/**
 * Created by evan on 2018/12/2.
 */
public class ServiceUtils {

    public static String genServiceName(String className) {
        return className.replace("Impl", "");
    }
}
