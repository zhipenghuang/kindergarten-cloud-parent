package com.zc.kindergarten.common.util;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
}
