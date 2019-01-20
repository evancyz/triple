package com.evanyz.triple.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * Created by evan on 2017/7/9.
 */
public class ReflectionUtils {


    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance =cls.newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     */
    public static Object invokeMethod(Object obj, Method method,Object...args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,args);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
