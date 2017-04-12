/**
 */
package com.open.taogubaweex.module;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXWeb;

public class WeeXWebViewModule extends WXModule{

    private enum Action {
        reload,
        goBack,
        goForward
    }

    @JSMethod(uiThread = true)
    public void goBack(String ref) {
        action(Action.goBack, ref);
    }

    @JSMethod(uiThread = true)
    public void goForward(String ref) {
        action(Action.goForward, ref);
    }

    @JSMethod(uiThread = true)
    public void reload(String ref) {
        action(Action.reload, ref);
    }

    private void action(Action action, String ref) {

        WXComponent webComponent =
                WXSDKManager.getInstance()
                        .getWXRenderManager()
                        .getWXComponent(mWXSDKInstance.getInstanceId(), ref);
        if(webComponent instanceof WXWeb) {
            ((WXWeb) webComponent).setAction(action.name());
        }
    }

}
