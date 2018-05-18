package com.hong_world.common.net.interceptor;

import com.hong_world.common.GlobalContants;
import com.hong_world.library.net.interceptor.base.BaseDynamicInterceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * Date: 2018/5/17. 14:44
 * Author: hong_world
 * Description: <p>描述：对参数进行签名、添加token、时间戳处理的拦截器</p>
 * 主要功能说明：<br>
 * 因为参数签名没办法统一，签名的规则不一样，签名加密的方式也不同有MD5、BASE64等等，只提供自己能够扩展的能力。<br>
 * Version:
 */

public class CustomSignInterceptor extends BaseDynamicInterceptor<CustomSignInterceptor> {
    @Override
    public TreeMap<String, String> dynamic(TreeMap<String, String> dynamicMap) {
        //dynamicMap:是原有的全局参数+局部参数
        if (isTimeStamp()) {//是否添加时间戳，因为你的字段key可能不是timestamp,这种动态的自己处理
            dynamicMap.put(GlobalContants.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        }
        if (isAccessToken()) {//是否添加token
            dynamicMap.put(GlobalContants.ACCESSTOKEN, "");
        }
        if (isSign()) {//是否签名,因为你的字段key可能不是sign，这种动态的自己处理
            dynamicMap.put(GlobalContants.SIGN, sign(dynamicMap));
        }
        //HttpLog.i("dynamicMap:" + dynamicMap.toString());
        return dynamicMap;//dynamicMap:是原有的全局参数+局部参数+新增的动态参数
    }

    //签名规则：
    private String sign(TreeMap<String, String> dynamicMap) {
        return "sign";
    }
}

