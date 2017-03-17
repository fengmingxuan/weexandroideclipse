/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-17下午5:53:07
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.module;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;
import com.taobao.weex.ui.component.WXWeb;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-17下午5:53:07
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class WXWebViewModule extends WXModule {

    private enum Action {
        reload,
        goBack,
        goForward
    }

    @WXModuleAnno
    public void goBack(String ref) {
        action(Action.goBack, ref);
    }

    @WXModuleAnno
    public void goForward(String ref) {
        action(Action.goForward, ref);
    }

    @WXModuleAnno
    public void reload(String ref) {
        action(Action.reload, ref);
    }

    private void action(Action action, String ref) {
        WXWeb webComponent = (WXWeb) WXSDKManager.getInstance().getWXRenderManager()
                                                .getWXComponent(mWXSDKInstance.getInstanceId(), ref);
        webComponent.setAction(action.name());
    }

}