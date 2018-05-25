package com.hong_world.library.net.exception;

/**
 * Date: 2018/5/24. 14:24
 * Author: hong_world
 * Description:
 * Version:
 */

public class NetCodeConfig {
    public static final int CODE_PARAMETER_ERROR = 50001;
    public static final int CODE_NO_DATA = 50002;
    public static final int CODE_OTHER_ERROR = 50003;
    public static final int CODE_UNKNOWN_ERROR = 50004;
    //AccessToken错误或已过期
    public static final int ACCESS_TOKEN_EXPIRED = 100010101;
    //RefreshToken错误或已过期
    public static final int REFRESH_TOKEN_EXPIRED = 100010102;
    //timestamp过期
    public static final int TIMESTAMP_ERROR = 100010104;
    //签名错误
    public final static int ERROR_SIGN = 100010105;
    //帐号在其它手机已登录
    public static final int OTHER_PHONE_LOGINED = 100021006;

}
