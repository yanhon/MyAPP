package com.hong_world.common.adapters;

import android.databinding.InverseMethod;

/**
 * Date: 2018/11/5. 17:14
 * Author: hong_world
 * Description:
 * Version:
 */
public class InverseMethodDemo {
    @InverseMethod("orderTypeToString")
    public static int stringToOrderType(String value) {
        if (value == null) {
            return 0;
        }
        switch (value) {
            case "立即单":
                return  1;
            case "预约单":
                return 2;
            case "接机单":
                return 3;
            case "送机单":
                return 4;
            case "半日租单":
                return 5;
            case "全日租单":
                return 6;
            default:
                return 0;
        }
    }
    public static String orderTypeToString(int code) {
        if (code ==0) {
            return null;
        }
        switch (code) {
            case  1:
                return "立即单";
            case 2:
                return "预约单";
            case 3:
                return "接机单";
            case 4:
                return "送机单";
            case 5:
                return "半日租单";
            case 6:
                return "全日租单";
            default:
                return null;
        }
    }
}
