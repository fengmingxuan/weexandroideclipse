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

import java.io.File;

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
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
	public String url =  WeexUtils.HTTP+"://"+WeexUtils.IP+WeexUtils.MAIN_WEB;
	private static final String APP_CACAHE_DIRNAME = "/webcache";  
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
		
		webSettings.setRenderPriority(RenderPriority.HIGH);  
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式  
        // 开启 DOM storage API 功能  
		webSettings.setDomStorageEnabled(true);  
        //开启 database storage API 功能  
		webSettings.setDatabaseEnabled(true);   
        String cacheDirPath = getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME;  
//      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;  
        Log.i(TAG, "cacheDirPath="+cacheDirPath);  
        //设置数据库缓存路径  
        webSettings.setDatabasePath(cacheDirPath);  
        //设置  Application Caches 缓存目录  
        webSettings.setAppCachePath(cacheDirPath);  
        //开启 Application Caches 功能  
        webSettings.setAppCacheEnabled(true);  
		
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
		
		@Override  
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {  
            Log.e(TAG, "onJsAlert " + message);  
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();  
            result.confirm();  
            return true;  
        }  

        @Override  
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {  
            Log.e(TAG, "onJsConfirm " + message);  
            return super.onJsConfirm(view, url, message, result);  
        }  

        @Override  
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {  
            Log.e(TAG, "onJsPrompt " + url);  
            return super.onJsPrompt(view, url, message, defaultValue, result);  
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
	
	/** 
     * 清除WebView缓存 
     */  
    public void clearWebViewCache(){  
        //清理Webview缓存数据库  
        try {  
            deleteDatabase("webview.db");   
            deleteDatabase("webviewCache.db");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        //WebView 缓存文件  
        File appCacheDir = new File(getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);  
        Log.e(TAG, "appCacheDir path="+appCacheDir.getAbsolutePath());  
          
        File webviewCacheDir = new File(getCacheDir().getAbsolutePath()+"/webviewCache");  
        Log.e(TAG, "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());  
          
        //删除webview 缓存目录  
        if(webviewCacheDir.exists()){  
            deleteFile(webviewCacheDir);  
        }  
        //删除webview 缓存 缓存目录  
        if(appCacheDir.exists()){  
            deleteFile(appCacheDir);  
        }  
    }  
      
    /** 
     * 递归删除 文件/文件夹 
     *  
     * @param file 
     */  
    public void deleteFile(File file) {  
        Log.i(TAG, "delete file path=" + file.getAbsolutePath());  
        if (file.exists()) {  
            if (file.isFile()) {  
                file.delete();  
            } else if (file.isDirectory()) {  
                File files[] = file.listFiles();  
                for (int i = 0; i < files.length; i++) {  
                    deleteFile(files[i]);  
                }  
            }  
            file.delete();  
        } else {  
            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());  
        }  
    }  

	public static void startUmeiWebViewActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, WeexWebViewActivity.class);
		context.startActivity(intent);
	}
}
