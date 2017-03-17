///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-3-17下午5:54:39
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.taogubaweex.component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import android.text.TextUtils;
//
//import com.taobao.weex.WXSDKInstance;
//import com.taobao.weex.WXSDKManager;
//import com.taobao.weex.dom.WXDomObject;
//import com.taobao.weex.ui.component.WXComponent;
//import com.taobao.weex.ui.component.WXComponentProp;
//import com.taobao.weex.ui.component.WXVContainer;
//import com.taobao.weex.ui.view.IWebView;
//import com.taobao.weex.ui.view.WXWebView;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-3-17下午5:54:39
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class WXWeb extends WXComponent {
//
//    protected IWebView mWebView;
//    private String mUrl;
//    private boolean mUrlChanged;
//
//    public WXWeb(WXSDKInstance instance, WXDomObject dom, WXVContainer parent,
//     String instanceId, boolean isLazy) {
//        super(instance, dom, parent, instanceId, isLazy);
//        createView();
//    }
//
//    // 创建
//    protected void  createView(){
//        mWebView = new WXWebView(mContext);
//    }
//
//    @Override
//    protected void initView() {
//        mWebView.setOnErrorListener(new IWebView.OnErrorListener() {
//            @Override
//            public void onError(String type, Object message) {
//                fireEvent(type, message);
//            }
//        });
//        mWebView.setOnPageListener(new IWebView.OnPageListener() {
//            @Override
//            public void onReceivedTitle(String title) {
//                if (mDomObj.event != null &&
//                 mDomObj.event.contains(WXEventType.WEBVIEW_RECEIVEDTITLE)) {
//                    Map<String, Object> params = new HashMap<>();
//                    params.put("title", title);
//                    WXSDKManager.getInstance()
//                    .fireEvent(mInstanceId, getRef(), WXEventType.WEBVIEW_RECEIVEDTITLE, params);
//                }
//            }
//
//            @Override
//            public void onPageStart(String url) {
//                if (mDomObj.event != null && mDomObj.event.contains(WXEventType.WEBVIEW_PAGESTART)) {
//                    Map<String, Object> params = new HashMap<>();
//                    params.put("url", url);
//                    WXSDKManager.getInstance()
//                    .fireEvent(mInstanceId, getRef(), WXEventType.WEBVIEW_PAGESTART, params);
//                }
//            }
//
//            @Override
//            public void onPageFinish(String url, boolean canGoBack, boolean canGoForward) {
//                if (mDomObj.event != null && 
//                    mDomObj.event.contains(WXEventType.WEBVIEW_PAGEFINISH)) {
//                    Map<String, Object> params = new HashMap<>();
//                    params.put("url", url);
//                    params.put("canGoBack", canGoBack);
//                    params.put("canGoForward", canGoForward);
//                    WXSDKManager.getInstance()
//                    .fireEvent(mInstanceId, getRef(), WXEventType.WEBVIEW_PAGEFINISH, params);
//                }
//            }
//        });
//        mHost = mWebView.getView();
//    }
//
//    @Override
//    public void flushView() {
//        super.flushView();
//        if (!TextUtils.isEmpty(mUrl) && mUrlChanged) {
//            mUrlChanged = false;
//            loadUrl(mUrl);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//        getWebView().destroy();
//    }
//
//    // is show loading
//    @WXComponentProp(name = "show-loading")
//    public void setShowLoading(boolean showLoading) {
//        getWebView().setShowLoading(showLoading);
//    }
//
//    // url
//    @WXComponentProp(name = "src")
//    public void setUrl(String url) {
//        if (TextUtils.isEmpty(url) || mHost == null) {
//            return;
//        }
//        mUrl = url;
//        mUrlChanged = true;
//    }
//
//    // 意图处理
//    public void setAction(String action) {
//        if (!TextUtils.isEmpty(action)) {
//            if (action.equals("goBack")) {
//                goBack();
//            } else if (action.equals("goForward")) {
//                goForward();
//            } else if (action.equals("reload")) {
//                reload();
//            }
//        }
//    }
//
//    private void fireEvent(String type, Object message) {
//        if (mDomObj.event != null && mDomObj.event.contains(WXEventType.WEBVIEW_ERROR)) {
//            Map<String, Object> params = new HashMap<>();
//            params.put("type", type);
//            params.put("errorMsg", message);
//            WXSDKManager.getInstance()
//            .fireEvent(mInstanceId, getRef(), WXEventType.WEBVIEW_ERROR, params);
//        }
//    }
//
//    private void loadUrl(String url) {
//        getWebView().loadUrl(url);
//    }
//
//    private void reload() {
//        getWebView().reload();
//    }
//
//    private void goForward() {
//        getWebView().goForward();
//    }
//
//    private void goBack() {
//        getWebView().goBack();
//    }
//
//    private IWebView getWebView() {
//        return mWebView;
//    }
//
//}
