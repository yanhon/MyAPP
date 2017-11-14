package com.hong_world.library.iloader;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

/**
 * Date: 2017/11/14.9:49
 * Author: hong_world
 * Description: Fresco库的实现
 * Version:
 */

public class FrescoLoader implements ILoader {
    private Context context;

    @Override
    public void init(Context context) {
        try {
            Class.forName("com.facebook.drawee.view.SimpleDraweeView");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Must be dependencies Fresco!");
        }
        if (context == null) {
            throw new NullPointerException("this context is null.");
        }
        this.context = context;
        Fresco.initialize(context.getApplicationContext());
    }

    @Override
    public void loadNet(ImageView target, String url, Options options) {
        Uri uri = Uri.parse(url);
        load(target, uri, options);
    }

    @Override
    public void loadResource(ImageView target, int resId, Options options) {
        Uri uri = Uri.parse("res://" + context.getPackageName() + "/" + resId);
        load(target, uri, options);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, Options options) {
        Uri uri = Uri.parse("asset:///" + assetName);
        load(target, uri, options);
    }

    @Override
    public void loadFile(ImageView target, File file, Options options) {
        if (file == null) {
            return;
        }
        Uri uri = Uri.parse("file://" + file.getPath());
        load(target, uri, options);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    @Override
    public void clearDiskCache(Context context) {
        Fresco.getImagePipeline().clearDiskCaches();
    }

    private void load(ImageView target, Uri uri, Options options) {
        if (target == null) {
            throw new NullPointerException("this imageView is null.");
        }
        if (options == null) options = Options.defaultOptions();
        if (target instanceof SimpleDraweeView) {
            Log.i("test", "Fresco Load SimpleDraweeView Path:" + uri.getPath());
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) target;
            simpleDraweeView.setImageURI(uri);
            if (options.loadingResId != Options.RES_NONE) {
                simpleDraweeView.getHierarchy().setPlaceholderImage(options.loadingResId);
            }
            if (options.loadErrorResId != Options.RES_NONE) {
                simpleDraweeView.getHierarchy().setFailureImage(options.loadErrorResId);
            }
        } else {
            Log.i("test", "Fresco Load ImageView must be SimpleDraweeView");
        }
    }
}
