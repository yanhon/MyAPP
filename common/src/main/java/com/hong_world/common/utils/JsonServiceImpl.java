package com.hong_world.common.utils;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;

import java.lang.reflect.Type;

/**
 * Date: 2018/3/27. 11:52
 * Author: hong_world
 * Description: 传递实体对象
 * Version:
 */
@Route(path = "/service/json",name = "传递自定义对象")
public class JsonServiceImpl implements SerializationService {
    @Override
    public void init(Context context) {

    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return GsonUtils.fromGson(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return GsonUtils.toGson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return GsonUtils.fromGson(input, clazz);
    }
}
