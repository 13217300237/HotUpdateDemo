package com.example.administrator.myapplication.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    //得到指定类的指定成员变量 ，兼容该成员在父类中的情况
    public static Field getField(Object instance, String fieldName) {
        //下面这个，兼容该成员变量在父类中的情况
        //解读：初始，clazz是当前类; 循环执行判定 clazz不为空； 变量变化：clazz赋值为它的父类
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(fieldName);//这个只是在本类中去找，如果我要的成员在父类中呢？
                if (!field.isAccessible())
                    field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
            }
        }
        return null;
    }

    //得到指定类的指定成员方法 ，兼容该成员在父类中的情况
    public static Method getMethod(Object instance, String methodName, Class<?>... args) {
        //下面这个，兼容该成员变量在父类中的情况
        //解读：初始，clazz是当前类; 循环执行判定 clazz不为空； 变量变化：clazz赋值为它的父类
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(methodName, args);//这个只是在本类中去找，如果我要的成员在父类中呢？
                if (!method.isAccessible())
                    method.setAccessible(true);
                return method;

            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
            }
        }
        return null;
    }


}
