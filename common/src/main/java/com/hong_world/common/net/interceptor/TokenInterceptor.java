package com.hong_world.common.net.interceptor;

import com.google.gson.Gson;
import com.hong_world.common.net.BaseResponse;
import com.hong_world.library.net.interceptor.base.BaseExpiredInterceptor;

import java.io.IOException;

import okhttp3.Response;

import static com.hong_world.library.net.exception.NetCodeConfig.ACCESS_TOKEN_EXPIRED;
import static com.hong_world.library.net.exception.NetCodeConfig.ERROR_SIGN;
import static com.hong_world.library.net.exception.NetCodeConfig.OTHER_PHONE_LOGINED;
import static com.hong_world.library.net.exception.NetCodeConfig.REFRESH_TOKEN_EXPIRED;
import static com.hong_world.library.net.exception.NetCodeConfig.TIMESTAMP_ERROR;

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
            if (code == ACCESS_TOKEN_EXPIRED
                    || code == REFRESH_TOKEN_EXPIRED
                    || code == OTHER_PHONE_LOGINED
                    || code == ERROR_SIGN
                    || code == TIMESTAMP_ERROR
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
                case ACCESS_TOKEN_EXPIRED: //AccessToken错误或已过期
                    refreshToken();

                    break;
//                    case APIResultException.NO_ACCESS_TOKEN://缺少授权信息,没有accessToken,应该是没有登录
                case REFRESH_TOKEN_EXPIRED://RefreshToken错误或已过期
                    reLogin();

                    break;
                case OTHER_PHONE_LOGINED://帐号在其它手机已登录

                    notifyLoginExit(baseResponse.getMsg());
                    break;
                case ERROR_SIGN://签名错误
                    break;
                case TIMESTAMP_ERROR://timestamp过期
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
