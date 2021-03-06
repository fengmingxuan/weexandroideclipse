/**
 */
package com.open.taogubaweex.component;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import java.util.HashMap;
import java.util.Map;
@Component(lazyload = false)

public class WeeXWeb extends WXComponent {

    public static final String GO_BACK = "goBack";
    public static final String GO_FORWARD = "goForward";
    public static final String RELOAD = "reload";
    public static final String CALL_NATIVE_H5 = "callnativeh5";
    protected IWeexWebView mWebView;

    @Deprecated
    public WeeXWeb(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String instanceId, boolean isLazy) {
        this(instance,dom,parent,isLazy);
    }

    public WeeXWeb(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
        super(instance, dom, parent, isLazy);
        createWebView();
    }

    protected void  createWebView(){
        mWebView = new WeeXWebView(getContext());
    }

    @Override
    protected View initComponentHostView(@NonNull Context context) {
        mWebView.setOnErrorListener(new IWeexWebView.OnErrorListener() {
            @Override
            public void onError(String type, Object message) {
                fireEvent(type, message);
            }
        });
        mWebView.setOnPageListener(new IWeexWebView.OnPageListener() {
            @Override
            public void onReceivedTitle(String title) {
                if (getDomObject().getEvents().contains(Constants.Event.RECEIVEDTITLE)) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("title", title);
                    fireEvent(Constants.Event.RECEIVEDTITLE, params);
                }
            }

            @Override
            public void onPageStart(String url) {
                if ( getDomObject().getEvents().contains(Constants.Event.PAGESTART)) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("url", url);
                    fireEvent(Constants.Event.PAGESTART, params);
                }
            }

            @Override
            public void onPageFinish(String url, boolean canGoBack, boolean canGoForward) {
                if ( getDomObject().getEvents().contains(Constants.Event.PAGEFINISH)) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("url", url);
                    params.put("canGoBack", canGoBack);
                    params.put("canGoForward", canGoForward);
                    fireEvent(Constants.Event.PAGEFINISH, params);
                }
            }
        });
        return mWebView.getView();
    }

    @Override
    public void destroy() {
        super.destroy();
        getWebView().destroy();
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case Constants.Name.SHOW_LOADING:
                Boolean result = WXUtils.getBoolean(param,null);
                if (result != null)
                    setShowLoading(result);
                return true;
            case Constants.Name.SRC:
                String src = WXUtils.getString(param,null);
                if (src != null)
                    setUrl(src);
                return true;
        }
        return super.setProperty(key,param);
    }

    @WXComponentProp(name = Constants.Name.SHOW_LOADING)
    public void setShowLoading(boolean showLoading) {
        getWebView().setShowLoading(showLoading);
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setUrl(String url) {
        if (TextUtils.isEmpty(url) || getHostView() == null) {
            return;
        }
        if (!TextUtils.isEmpty(url)) {
            loadUrl(getInstance().rewriteUri(Uri.parse(url), URIAdapter.WEB).toString());
        }
    }

    public void setAction(String action,String ref,String json) {
        if (!TextUtils.isEmpty(action)) {
            if (action.equals(GO_BACK)) {
                goBack();
            } else if (action.equals(GO_FORWARD)) {
                goForward();
            } else if (action.equals(RELOAD)) {
                reload();
            } else if (action.equals(CALL_NATIVE_H5)) {
            	callnativeh5(ref,json);
            }
        }
    }

    private void fireEvent(String type, Object message) {
        if (getDomObject().getEvents().contains(Constants.Event.ERROR)) {
            Map<String, Object> params = new HashMap<>();
            params.put("type", type);
            params.put("errorMsg", message);
            fireEvent(Constants.Event.ERROR, params);
        }
    }

    private void loadUrl(String url) {
        getWebView().loadUrl(url);
    }

    private void reload() {
        getWebView().reload();
    }
    
    private void callnativeh5(String ref,String json) {
        getWebView().callnativeh5(ref,json);
    }

    private void goForward() {
        getWebView().goForward();
    }

    private void goBack() {
        getWebView().goBack();
    }

    private IWeexWebView getWebView() {
        return mWebView;
    }

}
