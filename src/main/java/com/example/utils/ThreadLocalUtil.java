package com.example.utils;

public class ThreadLocalUtil {

    //提供ThreadLocal物件
    private static final ThreadLocal threadLocal = new ThreadLocal();

    //get
    public static <T> T get() {
        return (T) threadLocal.get();
    }

    //set

    public static void set(Object value){
        threadLocal.set(value);
    }

    //清除

    public static void remove(){
        threadLocal.remove();
    }
}
