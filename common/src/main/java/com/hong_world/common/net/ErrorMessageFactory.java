package com.hong_world.common.net;

import android.content.res.Resources;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.hong_world.common.GlobalContants;
import com.hong_world.common.utils.NetworkUtils;
import com.hong_world.library.net.exception.APIResultException;
import com.hong_world.library.net.exception.HttpStatusException;
import com.hong_world.library.utils.StringUtil;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

import static com.hong_world.library.net.exception.NetCodeConfig.CODE_NO_DATA;
import static com.hong_world.library.net.exception.NetCodeConfig.CODE_OTHER_ERROR;
import static com.hong_world.library.net.exception.NetCodeConfig.CODE_PARAMETER_ERROR;
import static com.hong_world.library.net.exception.NetCodeConfig.CODE_UNKNOWN_ERROR;

/**
 * Date: 2018/5/24. 11:37
 * Author: hong_world
 * Description:
 * Version:
 */

public class ErrorMessageFactory {
    private static final String TAG = "ErrorMessageFactory";
    private static APIResultException mTDException;

    public ErrorMessageFactory() {
    }

    public static ErrorMsgBean create(Exception exception) {
        ErrorMsgBean bean = new ErrorMsgBean();
        bean.setCode("");
        String message = "网络请求失败";
        if (StringUtil.isNotEmpty(exception.getMessage())) {
            Logger.e(TAG, exception.getMessage());
            message = exception.getMessage();
        }

        if (exception instanceof ConnectException) {
//            message = "无法连接到服务器，请稍后再试" + exception.toString();
            if (NetworkUtils.isConnected()) {
                bean.setCode(GlobalContants.NOTFIND);
                message = "无法连接到服务器，请稍后再试";
            } else {
                bean.setCode(GlobalContants.NONETWORK);
                message = "请检查网络连接";
            }
        } else if (exception instanceof JsonSyntaxException) {
            message = "json解析错误" + exception.toString();
        } else if (exception instanceof InterruptedException) {
            bean.setCode(GlobalContants.TIMEOUT);
            message = "连接超时" + exception.toString();
        } else if (exception instanceof SocketTimeoutException) {
            bean.setCode(GlobalContants.TIMEOUT);
            message = "请求超时" + exception.toString();
        } else if (exception instanceof Resources.NotFoundException || exception instanceof UnknownHostException) {
            if (NetworkUtils.isConnected()) {
                bean.setCode(GlobalContants.NOTFIND);
                message = "没有找到" + exception.toString();
            } else {
                bean.setCode(GlobalContants.NONETWORK);
                message = "请检查网络连接";
            }
        } else if (exception instanceof HttpException) {
            message = exception.getMessage();
        } else if (exception instanceof MalformedJsonException) {
            message = "sessionId错误" + exception.toString();
        } else if (exception instanceof NullPointerException) {
            message = "空指针异常->" + exception.toString();
        } else if (exception instanceof HttpStatusException) {
            message = ((HttpStatusException) exception).getErrorMsg();
        } else if (exception instanceof APIResultException) {
            mTDException = (APIResultException) exception;
            if (mTDException.getErrorCode() == CODE_NO_DATA) {
                bean.setCode(GlobalContants.DATAEMPTY);
                message = StringUtil.isEmpty(mTDException.getErrorMsg()) ? "暂无数据" : mTDException.getErrorMsg();
            } else if (mTDException.getErrorCode() == CODE_PARAMETER_ERROR) {
                message = StringUtil.isEmpty(mTDException.getErrorMsg()) ? "请求参数错误" : mTDException.getErrorMsg();
            } else if (mTDException.getErrorCode() == CODE_OTHER_ERROR) {
                message = StringUtil.isEmpty(mTDException.getErrorMsg()) ? "其他错误" : mTDException.getErrorMsg();
            } else if (mTDException.getErrorCode() == CODE_UNKNOWN_ERROR) {
                message = StringUtil.isEmpty(mTDException.getErrorMsg()) ? "未知错误" : mTDException.getErrorMsg();
            } else {
                message = !StringUtil.isEmpty(mTDException.getErrorMsg()) ? mTDException.getErrorMsg() : "未知错误";
            }
        }
        bean.setMsg(message);

        return bean;
    }

}
