/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午4:01:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.component;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午4:01:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class RichText extends WXComponent {
	private TextView mHost;
	public RichText(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
		super(instance, dom, parent, isLazy);
	}

	@Override
	protected void initView() {
		mHost = new TextView(getContext());
		((TextView) mHost).setMovementMethod(LinkMovementMethod.getInstance());
	}

	@WXComponentProp(name = "tel")
	public void setTelLink(String tel) {
		SpannableString spannable = new SpannableString(tel);
		spannable.setSpan(new URLSpan("tel:" + tel), 0, tel.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		((TextView) mHost).setText(spannable);
	}
}
