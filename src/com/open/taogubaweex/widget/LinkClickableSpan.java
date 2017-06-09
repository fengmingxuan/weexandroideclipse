/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-6上午11:28:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.widget;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-6上午11:28:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class LinkClickableSpan extends ClickableSpan {
	private String url;  
	private Context context;
	
	public LinkClickableSpan(Context context,String url){
		this.context = context;
		this.url = url;
	}
	
	/* (non-Javadoc)
	 * @see android.text.style.ClickableSpan#onClick(android.view.View)
	 */
	@Override
	public void onClick(View widget) {
		 Log.d("LinkClickableSpan", "url=="+url);
	}

}
