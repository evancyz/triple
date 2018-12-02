package com.evanyz.triple.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by evan on 2018/12/2.
 */
public class ServiceUtils {

    public static String genServiceName(String className) {

        String name = className.substring(className.lastIndexOf(".") + 1, className.length());

        name = name.replace("Impl", "");

        return StringUtils.uncapitalize(name);
    }
}
