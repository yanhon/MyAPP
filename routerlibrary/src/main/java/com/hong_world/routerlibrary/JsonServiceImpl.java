package com.hong_world.routerlibrary;

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
@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return null;
    }

    @Override
    public String object2Json(Object instance) {
        return null;
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return null;
    }

    @Override
    public void init(Context context) {

    }
}
