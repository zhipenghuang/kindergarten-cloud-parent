package com.zc.kindergarten.common.context;

import com.zc.kindergarten.common.constant.CommonConstants;
import com.zc.kindergarten.common.util.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class BaseContextHandler {

    //InheritableThreadLocal线程上下文切换父线程会赋值给新线程，feign开启hystrix后，
    // 如果hystrix选择官方推荐的thread策略，会导致header丢失，以此解决。
    // 但是InheritableThreadLocal不能解决线程复用带来的问题，如果后期出现问题，原因可能在这儿
    public static ThreadLocal<Map<String, Object>> threadLocal = new InheritableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserID() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getUsername() {
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }


    public static String getName() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setName(String name) {
        set(CommonConstants.CONTEXT_KEY_USER_NAME, name);
    }

    public static void setUserID(String userID) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(CommonConstants.CONTEXT_KEY_USERNAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
