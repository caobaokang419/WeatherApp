package com.gary.weatherdemo.utils;

import java.lang.reflect.Method;

/**
 * Created by GaryCao on 2019/01/27.
 * <p>
 * TODO:
 */
public class ReflectUtil {

    /**
     * 执行类方法（静态）
     *
     * @param class_name  类
     * @param method_name 静态方法
     * @return
     */
    public static void invokeStaticMethod(Class class_name, String method_name) {
        invokeStaticMethod(class_name, method_name, null);
    }

    /**
     * 执行类方法（静态）
     *
     * @param class_name  类
     * @param method_name 静态方法
     * @return
     */
    public static void invokeStaticMethod(Class class_name, String method_name, Object[] parameters) {
        try {
            Method getMethod = class_name.getDeclaredMethod(method_name);
            getMethod.invoke(class_name, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行实例对象方法（非静态）
     *
     * @param instance    对象实例
     * @param method_name 对象方法
     * @return
     */
    public static void invokeNoStaticMethod(Object instance, String method_name) {
        invokeNoStaticMethod(instance, method_name, null);
    }

    /**
     * 执行实例对象方法（非静态）
     *
     * @param instance    对象实例
     * @param method_name 对象方法
     * @param parameters  方法参数
     * @return
     */
    public static void invokeNoStaticMethod(Object instance, String method_name, Object[] parameters) {
        try {
            Method getMethod = instance.getClass().getDeclaredMethod(method_name);
            getMethod.invoke(instance, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类文件名
     */
    public static String getSimpleClassName(String className) {
        return className.substring(className.lastIndexOf(".") + 1);
    }
}