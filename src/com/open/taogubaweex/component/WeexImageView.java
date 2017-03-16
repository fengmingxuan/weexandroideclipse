/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-16下午4:02:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-3-16下午4:02:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class WeexImageView extends WXComponent {

	public WeexImageView(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
		super(instance, dom, parent, isLazy);
	}

//	@Override
//	protected View initComponentHostView(@NonNull Context context) {
//		return new KaolaImageView(context);
//	}
//
//	@Override
//	public View getHostView() {
//		return super.getHostView();
//	}
//
//	@WXComponentProp(name = "url")
//	public void setUrlStr(String url) {
//		ImageLoaderManager.startLoad(new ImageLoaderBuilder(((KaolaImageView) mHost), url));
//	}
	// 注册之后weex才能使用当前view
	// WXSDKEngine.registerComponent("kaolaimage", WeexImageView.class);
}