/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-10-17下午5:20:10
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.open.taogubaweex.utils.WeexUtils;

/**
 ***************************************************************************************************************************************************************************** 
 *  web页面
 * 
 * @author :fengguangjing
 * @createTime:2016-10-17下午5:20:10
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class WeexWebViewActivity extends  Activity {
	public static final String TAG = WeexWebViewActivity.class.getSimpleName();
	public WebView webview;
	public String url = WeexUtils.HTTP+"://"+WeexUtils.IP+WeexUtils.MAIN_JS;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_web);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		findView();
		initValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.CommonActivity#findView()
	 */
	protected void findView() {
		// TODO Auto-generated method stub
		webview = (WebView) findViewById(R.id.webview);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.CommonActivity#initValue()
	 */
	@SuppressLint("NewApi") protected void initValue() {
		// TODO Auto-generated method stub
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webview.setWebViewClient(mWebViewClientBase);
		webview.setWebChromeClient(mWebChromeClientBase);
		// 设置出现缩放工具
		webSettings.setBuiltInZoomControls(true);
		// 扩大比例的缩放
		webSettings.setUseWideViewPort(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  
			webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);  
		}  
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		}
		Log.i("WebViewActivity", "url==" + url);
		loadUrl();
	}
	
	public void loadUrl(){
		webview.loadUrl(url);
	}

	public WebViewClientBase mWebViewClientBase = new WebViewClientBase();

	public class WebViewClientBase extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
			// TODO Auto-generated method stub
			super.doUpdateVisitedHistory(view, url, isReload);
		}
		
		@Override  
        public void onReceivedSslError(WebView view,  
                SslErrorHandler handler, SslError error) {  
            handler.proceed();  
        } 
	}

	public WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

	private class WebChromeClientBase extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			// TODO Auto-generated method stub
			super.onReceivedTitle(view, title);
		}

		@Override
		public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
			// TODO Auto-generated method stub
			super.onReceivedTouchIconUrl(view, url, precomposed);
		}

		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			// TODO Auto-generated method stub
			return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (webview.canGoBack()) {
			webview.goBack();
		} else {
			super.onBackPressed();
		}
	}

	public static void startUmeiWebViewActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, WeexWebViewActivity.class);
		context.startActivity(intent);
	}
}
