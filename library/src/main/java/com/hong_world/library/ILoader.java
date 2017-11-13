package com.hong_world.library;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

/**
 * Date: 2017/11/13.17:04
 * Author: hong_world
 * Description: 图片加载接口
 * Version:
 */

public interface ILoader {
    void init(Context context);

    void loadNet(ImageView target, String url, Options options);

    void loadResource(ImageView target, int resId, Options options);

    void loadAssets(ImageView target, String assetName, Options options);

    void loadFile(ImageView target, File file, Options options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    class Options {

        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;//加载中的资源id
        public int loadErrorResId = RES_NONE;//加载失败的资源id

        public static Options defaultOptions() {
            return new Options(-1, -1);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
