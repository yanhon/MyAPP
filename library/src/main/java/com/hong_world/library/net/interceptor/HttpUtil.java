package com.hong_world.library.net.interceptor;

import com.orhanobut.logger.Logger;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Date: 2018/5/17. 13:56
 * Author: hong_world
 * Description:
 * Version:
 */

public class HttpUtil {
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static String createUrlFromParams(String url, Map<String, String> params) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            if (url.indexOf('&') > 0 || url.indexOf('?') > 0) sb.append("&");
            else sb.append("?");
            for (Map.Entry<String, String> urlParams : params.entrySet()) {
                String urlValues = urlParams.getValue();
                //对参数进行 utf-8 编码,防止头信息传中文
                //String urlValue = URLEncoder.encode(urlValues, UTF8.name());
                sb.append(urlParams.getKey()).append("=").append(urlValues).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return url;
    }
}
