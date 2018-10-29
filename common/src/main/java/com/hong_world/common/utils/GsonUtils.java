package com.hong_world.common.utils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

/**
 * Date: 2018/10/29. 11:53
 * Author: hong_world
 * Description:
 * Version:
 */
public class GsonUtils {
    private static Gson gson;

    public static String toGson(Object object) {
        if (gson == null) {
            gson = new Gson();
        }
        String json = gson.toJson(object);
        Logger.e(json);
        return json;
    }

    public static <T> T fromGson(String json, Class<T> classofT) {
        if (gson == null) {
            gson = new Gson();
        }
        Logger.e(json);
        return gson.fromJson(json, classofT);
    }

    public static <T> T fromGson(String json, Type clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        Logger.e(json);
        return gson.fromJson(json, clazz);
    }
}
