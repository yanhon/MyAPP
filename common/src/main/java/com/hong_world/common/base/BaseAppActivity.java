package com.hong_world.common.base;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.hong_world.common.R;
import com.hong_world.library.base.BaseSupportActivity;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Date: 2018/10/18. 11:10
 * Author: hong_world
 * Description:
 * Version:
 */
public abstract class BaseAppActivity extends BaseSupportActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean permissionPass = false;

    protected RxPermissions getRxPermissions() {
        return new RxPermissions(this);
    }

    protected boolean checkPermission(String msg, int fromType, final String... permissions) {
        permissionPass = false;
        compositeDisposable.add(getRxPermissions()
                .requestEachCombined(permissions)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Logger.i("用户同意了该权限");
                        permissionPass = true;
                        passPermission(fromType);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        Logger.i("用户拒绝了该权限，没有选中『不再询问』");
                    } else {
                        Logger.i("用户拒绝了该权限，选中『不再询问』");
                        rejectPermission(msg);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable.getMessage());
                    }
                }));
        return permissionPass;
    }

    /**
     * 同意权限后自动调起之前的操作，不重写则需用户再次点击操作
     *
     * @param fromType
     */
    protected void passPermission(@Nullable int fromType) {

    }

    protected void rejectPermission(String msg) {
//        showToast("您没有授权该权限，请在设置中打开授权");
        askForPermission(msg);
    }

    protected void askForPermission(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher);
        builder.setTitle("手动设置权限");
        builder.setMessage("如需正常使用该功能需要授予：" + msg + "权限" + "\n设置流程：应用信息—>权限中进行设置");
        builder.setNegativeButton("取消", (dialog, which) -> {

        });
        builder.setPositiveButton("去设置", (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName())); // 根据包名打开对应的设置界面
            startActivity(intent);
        });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
