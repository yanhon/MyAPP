package com.hong_world.common.net.interceptor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hong_world.common.net.BaseResponse;
import com.hong_world.library.net.exception.APIResultException;
import com.hong_world.library.net.interceptor.base.BaseExpiredInterceptor;

import java.io.IOException;

import okhttp3.Response;

/**
 * Date: 2018/5/17. 14:16
 * Author: hong_world
 * Description:
 * Version:
 */

public class TokenInterceptor extends BaseExpiredInterceptor {
    BaseResponse baseResponse;

    @Override
    public boolean isResponseExpired(Response response, String bodyString) {
        baseResponse = new Gson().fromJson(bodyString, BaseResponse.class);
        if (baseResponse != null) {
            int code = baseResponse.getErrorCode();
            if (code == APIResultException.ACCESS_TOKEN_EXPIRED
                    || code == APIResultException.REFRESH_TOKEN_EXPIRED
                    || code == APIResultException.OTHER_PHONE_LOGINED
                    || code == APIResultException.ERROR_SIGN
                    || code == APIResultException.TIMESTAMP_ERROR
                    ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response responseExpired(Chain chain, String bodyString) {
        try {
            switch (baseResponse.getErrorCode()) {
                case APIResultException.ACCESS_TOKEN_EXPIRED: //AccessToken错误或已过期
                    refreshToken();

                    break;
//                    case APIResultException.NO_ACCESS_TOKEN://缺少授权信息,没有accessToken,应该是没有登录
                case APIResultException.REFRESH_TOKEN_EXPIRED://RefreshToken错误或已过期
                    reLogin();

                    break;
                case APIResultException.OTHER_PHONE_LOGINED://帐号在其它手机已登录

                    notifyLoginExit(baseResponse.getMsg());
                    break;
                case APIResultException.ERROR_SIGN://签名错误
                    break;
                case APIResultException.TIMESTAMP_ERROR://timestamp过期
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //同步请求refreshToken
    public void refreshToken() throws IOException {
    }

    /**
     * 同步请求重新登录
     *
     * @return
     * @throws IOException
     */
    private void reLogin() throws IOException {
    }

    private void notifyLoginExit(String msg) {
    }

}
