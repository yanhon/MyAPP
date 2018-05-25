package com.hong_world.library.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by hong_world on 2017/4/24.
 */

public class StringUtil {
    public static boolean isEmpty(String... args) {
        if (args == null) return true;
        for (String item : args) {
            if (TextUtils.isEmpty(item)) return true;
        }
        return false;
    }

    private StringUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否非空
     *
     * @param obj 对象
     * @return {@code true}: 非空<br>{@code false}: 空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * @return
     */
    public static boolean isNumberText(String passwd) {
        try {

            if (!isEmpty(passwd) && passwd.matches("^[\\u4E00-\\u9FA5A-Za-z0-9]+$")) {
                return true;
            } else {
                Log.i("test", "not is 数字汉字英文");
                return false;
            }
        } catch (Exception e) {
            Log.i("test", e.getMessage().toString());
        }
        return false;
    }

    /**
     * 是否是身份证
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if (isEmpty(idCard)) {
            return false;
        } else {
            if (idCard.matches("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 是否为电话号码
     *
     * @param tellPhone
     * @return
     */
    public static boolean isTellPhone(String tellPhone) {

        if (isEmpty(tellPhone)) {
//            ToastUtil.showShort("不能为空！");
            return false;
        } else if (tellPhone.matches("^1[3|4|5|7|8][0-9]\\d{8}$")) {
            return true;
        } else {
            Log.i("test", "not is tellphone");
            return false;
        }
    }

    /**
     * 是否是验证码
     *
     * @param smsCode
     * @return
     */
    public static boolean isSmsCode(String smsCode) {
        if (!isEmpty(smsCode) || smsCode.matches("(?<!\\d)\\d{6}(?!\\d)")) {
            return true;
        } else {
            Log.i("test", "not is smscode");
            return false;
        }
    }

    /**
     * 密码格式
     *
     * @return
     */
    public static boolean isPwd(String passwd) {
        if (!isEmpty(passwd) && passwd.matches("^[0-9a-zA-Z]{6,16}$")) {
            return true;
        } else {
//            Log.i("test", "not is passwod");
            return false;
        }
    }

}
