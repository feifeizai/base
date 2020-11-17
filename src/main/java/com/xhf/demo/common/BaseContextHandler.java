package com.xhf.demo.common;

import java.util.HashMap;
import java.util.Map;


/**
 * 上下文处理基类
 * 保存一次请求中的公共参数
 */
public class BaseContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static void set(String key, Object value) {
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
        return (String) get(ContextConstants.CONTEXT_KEY_USER_ID);
    }

    public static String getUsername() {
        return (String) get(ContextConstants.CONTEXT_KEY_USERNAME);
    }

    public static String getToken() {
        return (String) get(ContextConstants.CONTEXT_KEY_USER_TOKEN);
    }

    public static void setToken(String token) {
        set(ContextConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setUserID(String userID) {
        set(ContextConstants.CONTEXT_KEY_USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(ContextConstants.CONTEXT_KEY_USERNAME, username);
    }

    public static void remove() {
        threadLocal.remove();
    }

}

