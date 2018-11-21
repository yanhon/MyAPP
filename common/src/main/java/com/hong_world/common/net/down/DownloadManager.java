package com.hong_world.common.net.down;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hong_world.common.utils.FileUtils;
import com.hong_world.common.utils.GsonUtils;
import com.hong_world.common.utils.SPUtils;
import com.hong_world.library.base.BaseApplication;
import com.hong_world.library.net.exception.APIResultException;
import com.hong_world.library.utils.StringUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.FlowableEmitter;
import okhttp3.ResponseBody;

import static com.hong_world.library.net.exception.NetCodeConfig.CODE_OTHER_ERROR;

/**
 * Date: 2018/11/21. 14:42
 * Author: hong_world
 * Description:
 * Version:
 */
public class DownloadManager {
    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";
    private static String PNG_CONTENTTYPE = "image/png";
    private static String JPG_CONTENTTYPE = "image/jpg";

    /**
     * 保存文件操作
     *
     * @param sub
     * @param path
     * @param start
     * @param resp
     * @param tagUrl
     */
    public static void saveFile(FlowableEmitter<? super DownProgress> sub, String path, long start, ResponseBody resp, String tagUrl) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File saveFile = new File(path);
        int readLen;
        long downloadSize = start;
        byte[] buffer = new byte[1024 * 2];
        long lastRefreshUiTime = 0;
        long contentLength = resp.contentLength();
        long oldTotalSize = 0;
        String oldPath = "";
        DownProgress downProgress = new DownProgress();
        boolean fileAppend = false;
        try {
            try {
                inputStream = resp.byteStream();
                if (start > 0) {//断点下载
                    String downProgressjson = SPUtils.getInstance().getString(tagUrl);
                    if (StringUtil.isNotEmpty(downProgressjson)) {
                        DownProgress oldDownProgress = GsonUtils.fromGson(downProgressjson, DownProgress.class);
                        oldTotalSize = oldDownProgress.getTotalSize();
                        oldPath = oldDownProgress.getPath();
                    }
                    if (path.equals(oldPath))//文件名是否相等
                        if (contentLength + start != oldTotalSize) {//判断断点前后数据大小不相等，不同一个文件，重新下载
                            SPUtils.getInstance().remove(tagUrl);
                            Logger.i("文件大小不一致需重新下载");
                            sub.onError(new APIResultException(CODE_OTHER_ERROR, "缓存文件大小不一致需重新下载"));
                            return;
                        }
                }
                Logger.i("contentLength=" + contentLength + ",start=" + start + ",oldTotalSize=" + oldTotalSize);
                if (sub.isCancelled()) {
                    Logger.i("取消下载0:" + downProgress.toString());
                    sub.onComplete();
                    return;
                }
                outputStream = new FileOutputStream(saveFile, start > 0);
                downProgress.setTotalSize(contentLength + start);
                downProgress.setPath(path);
                if (start == 0) {
                    SPUtils.getInstance().put(tagUrl, GsonUtils.toGson(downProgress));
                }
                while (!sub.isCancelled() && (readLen = inputStream.read(buffer)) != -1) {
                    if (sub.isCancelled()) {
                        Logger.i("取消下载1:" + downProgress.toString());
                        return;
                    }
                    outputStream.write(buffer, 0, readLen);
                    downloadSize += readLen;
                    downProgress.setDownloadSize(downloadSize);
                    Logger.i("saveFile:" + downProgress.toString());
                    //下载进度
                    float progress = downloadSize * 1.0f / contentLength;
                    long curTime = System.currentTimeMillis();
                    if (curTime - lastRefreshUiTime >= 200 || progress == 1.0f) {
                        if (sub.isCancelled()) {
                            Logger.i("取消下载2:" + downProgress.toString());
                            return;
                        }
                        sub.onNext(downProgress);
                        lastRefreshUiTime = System.currentTimeMillis();
                    }
                }
                outputStream.flush();
                sub.onComplete();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (resp != null) {
                    resp.close();
                }
            }
        } catch (IOException e) {
            if (sub.isCancelled())
                sub.onError(e);
        }
    }

    /**
     * fileName 传空时自行判断文件名称
     *
     * @param names
     * @param responseBody
     * @return
     */
    public static String getRealFileName(@Nullable String names, ResponseBody responseBody) {
        String name = names;
        String fileSuffix = "";
        if (!TextUtils.isEmpty(name)) {//text/html; charset=utf-8
            String type;
            if (!name.contains(".")) {
                type = responseBody.contentType().toString();
                if (type.equals(APK_CONTENTTYPE)) {
                    fileSuffix = ".apk";
                } else if (type.equals(PNG_CONTENTTYPE)) {
                    fileSuffix = ".png";
                } else if (type.equals(JPG_CONTENTTYPE)) {
                    fileSuffix = ".jpg";
                } else {
                    fileSuffix = "." + responseBody.contentType().subtype();
                }
                name = name + fileSuffix;
            }
        } else {
            name = System.currentTimeMillis() + fileSuffix;
        }
        return name;
    }

    /**
     * 判断文件是否下载完成，如下载完成则名称自动更改
     *
     * @param fileName 文件名称（包括后缀）
     * @param path     文件路劲
     * @param tagUrl   文件请求的路径
     * @return
     */
    public static File checkDownFile(String fileName, String path, String tagUrl) {
        long start = 0;
        long end = 0;
        String downProgressjson = SPUtils.getInstance().getString(tagUrl);
        if (StringUtil.isNotEmpty(downProgressjson)) {
            DownProgress oldDownProgress = GsonUtils.fromGson(downProgressjson, DownProgress.class);
            end = oldDownProgress.getTotalSize();
        }
        File file = new File(path, fileName);
        if (file.exists()) {
            start = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (start >= end && start != 0) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(path, fileNameOther);
            file = newFile;
            start = newFile.length();
            fileName = newFile.getName();
            i++;
        }
        return file;
    }

    /**
     * 检查子目录是否存在
     *
     * @param paths
     * @param name
     * @return
     */
    public static String getRealFilePath(@Nullable String paths, String name) {
        String path = paths;

        if (path == null) {
            String dirName = "down";
            File dir = new File(FileUtils.getDiskCachePath(BaseApplication.getInstance()) + File.separator + dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            path = FileUtils.getDiskCachePath(BaseApplication.getInstance()) + File.separator + dirName + File.separator + name;
        } else {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            path = path + File.separator + name;
            path = path.replaceAll("//", "/");
        }

        return path;
    }
}
