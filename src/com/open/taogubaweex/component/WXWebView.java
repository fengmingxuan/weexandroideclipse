/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-17下午5:55:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.component;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.taobao.weex.ui.view.IWebView;
import com.taobao.weex.utils.WXLogUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-17下午5:55:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class WXWebView implements IWebView {

    private Context mContext;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private boolean mShowLoading = true;
    private OnErrorListener mOnErrorListener;
    private OnPageListener mOnPageListener;

    public WXWebView(Context context) {
        mContext = context;
    }

    @Override
    public View getView() {
        FrameLayout root = new FrameLayout(mContext);
        root.setBackgroundColor(Color.WHITE);
        mWebView = new WebView(mContext);
        FrameLayout.LayoutParams wvLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                             FrameLayout.LayoutParams.MATCH_PARENT);
        wvLayoutParams.gravity = Gravity.CENTER;
        mWebView.setLayoutParams(wvLayoutParams);
        root.addView(mWebView);
        initWebView(mWebView);
        mProgressBar = new ProgressBar(mContext);
        showProgressBar(false);
        FrameLayout.LayoutParams pLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                                             FrameLayout.LayoutParams.WRAP_CONTENT);
        mProgressBar.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = Gravity.CENTER;
        root.addView(mProgressBar);
        return root;
    }

    @Override
    public void destroy() {
        if (getWebView() != null) {
            getWebView().removeAllViews();
            getWebView().destroy();
            mWebView = null;
        }
    }

    @Override
    public void loadUrl(String url) {
        getWebView().loadUrl(url);
    }

    @Override
    public void reload() {
        getWebView().reload();
    }

    @Override
    public void goBack() {
        getWebView().goBack();
    }

    @Override
    public void goForward() {
        getWebView().goForward();
    }

    @Override
    public void setShowLoading(boolean shown) {
        mShowLoading = shown;
    }

    @Override
    public void setOnErrorListener(OnErrorListener listener) {
        mOnErrorListener = listener;
    }

    @Override
    public void setOnPageListener(OnPageListener listener) {
        mOnPageListener = listener;
    }

    private void showProgressBar(boolean shown) {
        if (mShowLoading) {
            mProgressBar.setVisibility(shown ? View.VISIBLE : View.GONE);
        }
    }

    private void showWebView(boolean shown) {
        mWebView.setVisibility(shown ? View.VISIBLE : View.INVISIBLE);
    }

    private WebView getWebView() {
        return mWebView;
    }

    private void initWebView(WebView wv) {
        // 初始化
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        // 设置客户端
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                WXLogUtils.v("tag", "onPageOverride " + url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                WXLogUtils.v("tag", "onPageStarted " + url);
                if (mOnPageListener != null) {
                    mOnPageListener.onPageStart(url);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WXLogUtils.v("tag", "onPageFinished " + url);
                if (mOnPageListener != null) {
                    mOnPageListener.onPageFinish(url, view.canGoBack(), view.canGoForward());
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request
            , WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError("error", "page error");
                }
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request,
             WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError("error", "http error");
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError("error", "ssl error");
                }
            }

        });
        // 设置浏览器客户端
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                showWebView(newProgress == 100);
                showProgressBar(newProgress != 100);
                WXLogUtils.v("tag", "onPageProgressChanged " + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (mOnPageListener != null) {
                    mOnPageListener.onReceivedTitle(view.getTitle());
                }
            }

        });
    }

}