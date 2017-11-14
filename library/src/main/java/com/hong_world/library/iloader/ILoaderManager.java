package com.hong_world.library.iloader;

/**
 * Date: 2017/11/13.17:13
 * Author: hong_world
 * Description: 图片加载管理，可定制图片加载框架
 * Version:
 */

public class ILoaderManager {
    private static ILoader innerLoader;
    private static ILoader externalLoader;

    public static void setLoader(ILoader loader) {
        if (externalLoader == null && loader != null) {
            externalLoader = loader;
        }
    }

    public static ILoader getLoader() {
        if (innerLoader == null) {
            synchronized (ILoaderManager.class) {
                if (innerLoader == null) {
                    if (externalLoader != null) {
                        innerLoader = externalLoader;
                    } else {
                        innerLoader = new GlideLoader();
                    }
                }
            }
        }
        return innerLoader;
    }
}
