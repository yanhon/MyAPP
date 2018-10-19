package com.hong_world.common.base;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hong_world.common.R;
import com.hong_world.common.base.web.FragmentKeyDown;
import com.hong_world.common.base.web.MiddlewareChromeClient;
import com.hong_world.common.base.web.MiddlewareWebViewClient;
import com.hong_world.common.base.web.UIController;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;
import com.hong_world.library.utils.StringUtil;
import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.MiddlewareWebChromeBase;
import com.just.agentweb.MiddlewareWebClientBase;
import com.just.agentweb.PermissionInterceptor;
import com.just.agentweb.WebListenerManager;
import com.just.agentweb.download.AgentWebDownloader;
import com.just.agentweb.download.DefaultDownloadImpl;
import com.just.agentweb.download.DownloadListenerAdapter;
import com.just.agentweb.download.DownloadingService;
import com.orhanobut.logger.Logger;

/**
 * Date: 2018/10/18. 14:14
 * Author: hong_world
 * Description:
 * Version:
 */
public abstract class BaseWebViewFragment<P extends BasePresenter, V extends ViewDataBinding> extends BaseFragment<P, V>
        implements BaseView<P>, FragmentKeyDown {
    protected AgentWeb mAgentWeb;
    protected DownloadingService mDownloadingService;
    protected MiddlewareWebClientBase mMiddleWareWebClient;
    protected MiddlewareWebChromeBase mMiddleWareWebChrome;

    public void initWebView(@NonNull ViewGroup v, String loadUrl) {
        Logger.i("网页地址：" + loadUrl);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .useDefaultIndicator(Color.parseColor("#ff7733"), 2)//
                .setAgentWebWebSettings(getSettings())//设置 IAgentWebSettings。
//                .setWebViewClient(mWebViewClient)//WebViewClient ， 与 WebView 使用一致 ，但是请勿获取WebView调用setWebViewClient(xx)方法了,会覆盖AgentWeb DefaultWebClient,同时相应的中间件也会失效。
//                .setWebChromeClient(mWebChromeClient) //WebChromeClient
                .setPermissionInterceptor(mPermissionInterceptor) //权限拦截 2.0.0 加入。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setAgentWebUIController(new UIController(getActivity())) //自定义UI  AgentWeb3.0.0 加入。
                .setMainFrameErrorView(R.layout.layout_error, -1) //agentweb_error_page参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .useMiddlewareWebChrome(getMiddlewareWebChrome()) //设置WebChromeClient中间件，支持多个WebChromeClient，AgentWeb 3.0.0 加入。
                .useMiddlewareWebClient(getMiddlewareWebClient()) //设置WebViewClient中间件，支持多个WebViewClient， AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .interceptUnkownUrl() //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .createAgentWeb()//创建AgentWeb。
                .ready()//设置 WebSettings。
                .go(loadUrl); //WebView载入该url地址的页面并显示。
        if (!StringUtil.isEmpty(loadUrl))
            if (!URLUtil.isNetworkUrl(loadUrl) && !loadUrl.startsWith("file://")) {//html字符串
                mAgentWeb.getUrlLoader().loadDataWithBaseURL(null, loadUrl, "name/html", "utf-8", null);
            }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        if (mAgentWeb != null)
            mAgentWeb.getUrlLoader().reload();
        getSmartRefreshLayout().finishRefresh();
    }

    @Override
    public void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    /**
     * 引用的activity需要重写onKeyDown 以拦截返回事件
     *  @Override
     *     public boolean onKeyDown(int keyCode, KeyEvent event) {
     *         List<Fragment> fragments = getSupportFragmentManager().getFragments();
     *         if (fragments != null)
     *             for (Fragment fragment : fragments) {
     *                 if (fragment instanceof FragmentKeyDown && fragment.isVisible()) {
     *                     if (((FragmentKeyDown) fragment).onFragmentKeyDown(keyCode, event)) {
     *                         return true;
     *                     }
     *                 }
     *             }
     *         return super.onKeyDown(keyCode, event);
     *     }
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null) {
            return mAgentWeb.handleKeyEvent(keyCode, event);
        } else
            return false;
    }

    @Override
    public void onDestroyView() {
        if (mAgentWeb != null) {
            mAgentWeb.clearWebCache();
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroyView();
    }

    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        /**
         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
         * AgentWeb 是用自己的权限机制的 ，true 该Url对应页面请求定位权限拦截 ，false 默认允许。
         * @param url
         * @param permissions
         * @param action
         * @return
         */
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            Logger.i("mUrl:" + url + "  permission:" + permissions + " action:" + action);
            return false;
        }
    };
    /**
     * 更新于 AgentWeb  4.0.0
     */
    protected DownloadListenerAdapter mDownloadListenerAdapter = new DownloadListenerAdapter() {

        /**
         *
         * @param url                下载链接
         * @param userAgent          UserAgent
         * @param contentDisposition ContentDisposition
         * @param mimetype           资源的媒体类型
         * @param contentLength      文件长度
         * @param extra              下载配置 ， 用户可以通过 Extra 修改下载icon ， 关闭进度条 ， 是否强制下载。
         * @return true 表示用户处理了该下载事件 ， false 交给 AgentWeb 下载
         */
        @Override
        public boolean onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, AgentWebDownloader.Extra extra) {
            Logger.i("onStart:" + url);
            extra.setOpenBreakPointDownload(true) // 是否开启断点续传
                    .setIcon(R.drawable.ic_file_download_black_24dp) //下载通知的icon
                    .setConnectTimeOut(6000) // 连接最大时长
                    .setBlockMaxTime(10 * 60 * 1000)  // 以8KB位单位，默认60s ，如果60s内无法从网络流中读满8KB数据，则抛出异常
                    .setDownloadTimeOut(Long.MAX_VALUE) // 下载最大时长
                    .setParallelDownload(false)  // 串行下载更节省资源哦
                    .setEnableIndicator(true)  // false 关闭进度通知
                    .addHeader("Cookie", "xx") // 自定义请求头
                    .setAutoOpen(true) // 下载完成自动打开
                    .setForceDownload(true); // 强制下载，不管网络网络类型
            return false;
        }

        /**
         *
         * 不需要暂停或者停止下载该方法可以不必实现
         * @param url
         * @param downloadingService  用户可以通过 DownloadingService#shutdownNow 终止下载
         */
        @Override
        public void onBindService(String url, DownloadingService downloadingService) {
            super.onBindService(url, downloadingService);
            mDownloadingService = downloadingService;
            Logger.i("onBindService:" + url + "  DownloadingService:" + downloadingService);
        }

        /**
         * 回调onUnbindService方法，让用户释放掉 DownloadingService。
         * @param url
         * @param downloadingService
         */
        @Override
        public void onUnbindService(String url, DownloadingService downloadingService) {
            super.onUnbindService(url, downloadingService);
            mDownloadingService = null;
            Logger.i("onUnbindService:" + url);
        }

        /**
         *
         * @param url  下载链接
         * @param loaded  已经下载的长度
         * @param length    文件的总大小
         * @param usedTime   耗时 ，单位ms
         * 注意该方法回调在子线程 ，线程名 AsyncTask #XX 或者 AgentWeb # XX
         */
        @Override
        public void onProgress(String url, long loaded, long length, long usedTime) {
            int mProgress = (int) ((loaded) / Float.valueOf(length) * 100);
            Logger.i("onProgress:" + mProgress);
            super.onProgress(url, loaded, length, usedTime);
        }

        /**
         *
         * @param path 文件的绝对路径
         * @param url  下载地址
         * @param throwable    如果异常，返回给用户异常
         * @return true 表示用户处理了下载完成后续的事件 ，false 默认交给AgentWeb 处理
         */
        @Override
        public boolean onResult(String path, String url, Throwable throwable) {
            if (null == throwable) { //下载成功
                //do you work
            } else {//下载失败

            }
            return false; // true  不会发出下载完成的通知 , 或者打开文件
        }
    };

    public IAgentWebSettings getSettings() {
        return new AbsAgentWebSettings() {
            private AgentWeb mAgentWeb;

            @Override
            protected void bindAgentWebSupport(AgentWeb agentWeb) {
                this.mAgentWeb = agentWeb;
            }

            /**
             * AgentWeb 4.0.0 内部删除了 DownloadListener 监听 ，以及相关API ，将 Download 部分完全抽离出来独立一个库，
             * 如果你需要使用 AgentWeb Download 部分 ， 请依赖上 compile 'com.just.agentweb:download:4.0.0 ，
             * 如果你需要监听下载结果，请自定义 AgentWebSetting ， New 出 DefaultDownloadImpl，传入DownloadListenerAdapter
             * 实现进度或者结果监听，例如下面这个例子，如果你不需要监听进度，或者下载结果，下面 setDownloader 的例子可以忽略。
             * @param webView
             * @param downloadListener
             * @return WebListenerManager
             */
            @Override
            public WebListenerManager setDownloader(WebView webView, android.webkit.DownloadListener downloadListener) {
                return super.setDownloader(webView,
                        DefaultDownloadImpl
                                .create((Activity) webView.getContext(),
                                        webView,
                                        mDownloadListenerAdapter,
                                        mDownloadListenerAdapter,
                                        this.mAgentWeb.getPermissionInterceptor()));
            }

            @Override
            public WebSettings getWebSettings() {
                WebSettings settings = super.getWebSettings();
                settings.setBuiltInZoomControls(true);
                settings.setDisplayZoomControls(false);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);
                return settings;
            }
        };

    }

    protected MiddlewareWebChromeBase getMiddlewareWebChrome() {
        return this.mMiddleWareWebChrome = new MiddlewareChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setWebTitle(title);
            }
        };
    }

    public void setWebTitle(String title) {
        if (StringUtil.isEmpty(title))
            return;

        if (baseLayoutBinding != null) baseLayoutBinding.topTitle.setText(title);
    }

    /**
     * MiddlewareWebClientBase 是 AgentWeb 3.0.0 提供一个强大的功能，
     * 如果用户需要使用 AgentWeb 提供的功能， 不想重写 WebClientView方
     * 法覆盖AgentWeb提供的功能，那么 MiddlewareWebClientBase 是一个
     * 不错的选择 。
     *
     * @return
     */
    protected MiddlewareWebClientBase getMiddlewareWebClient() {
        return this.mMiddleWareWebClient = new MiddlewareWebViewClient();
    }
}
