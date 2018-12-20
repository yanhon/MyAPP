package com.hong_world.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.hong_world.common.R;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Date: 2018/12/20. 16:30
 * Author: hong_world
 * Description:
 * Version:
 */
public class PermissionUtlis {
    public static Disposable checkPermission(Fragment context, String msg, OnPermissionListener listener, String... permissions) {
        return checkPermissionImp(new RxPermissions(context), context.getActivity(), msg, listener, permissions);
    }

    public static Disposable checkPermission(FragmentActivity context, String msg, OnPermissionListener listener, String... permissions) {
        return checkPermissionImp(new RxPermissions(context), context, msg, listener, permissions);
    }

    private static Disposable checkPermissionImp(RxPermissions rxPermissions, Activity activity, String msg, OnPermissionListener listener, String... permissions) {
        return rxPermissions.requestEachCombined(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            Logger.i("用户同意了该权限");
                            listener.passPermission();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            Logger.i("用户拒绝了该权限，没有选中『不再询问』");
                            askForPermission(activity, msg);
                        } else {
                            Logger.i("用户拒绝了该权限，选中『不再询问』");
                            askForPermission(activity, msg);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable.getMessage());
                    }
                });
    }

    private static void askForPermission(Activity mActivity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity).setIcon(R.mipmap.ic_launcher);
        builder.setTitle("手动设置权限");
        builder.setMessage("如需正常使用该功能需要授予：" + msg + "权限" + "\n设置流程：应用信息—>权限中进行设置");
        builder.setNegativeButton("取消", (dialog, which) -> {

        });
        builder.setPositiveButton("去设置", (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + mActivity.getPackageName())); // 根据包名打开对应的设置界面
            mActivity.startActivity(intent);
        });
        builder.create().show();
    }

    public interface OnPermissionListener {
        void passPermission();
    }
}