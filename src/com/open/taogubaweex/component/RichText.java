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

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.open.taogubaweex.utils.CustomLinkMovementMethod;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

 public class RichText extends WXComponent<TextView> {

  public RichText(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
    super(instance, dom, parent, isLazy);
  }
  
  /* (non-Javadoc)
	 * @see com.taobao.weex.ui.component.WXComponent#initComponentHostView(android.content.Context)
	 */
	@Override
	protected TextView initComponentHostView(@NonNull Context context) {
		// TODO Auto-generated method stub
		return new TextView(context);
	}
	 
//  
//  @WXComponentProp(name = "tel")
//  public void setTelLink(String tel){
//    SpannableString spannable=new SpannableString(tel);
//    spannable.setSpan(new URLSpan("tel:"+tel),0,tel.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//    Log.d("RichText", "tel=="+tel);
//    ((TextView)getRealView()).setText(spannable);
//  }
//  
	/**
{
    "content":"1490952336000婷婷春蕾天啊123收快递费的撒娇",
    "myrichvalue":[
        {
            "onClickType":0,
            "strText":"天啊123收快递费的撒娇",
            "strTextColor":"#000000",
            "strTextSize":22
        },
        {
            "onClickType":0,
            "strText":"天啊123收快递费的撒娇",
            "strTextColor":"#000000",
            "strTextSize":22
        },
        {
            "onClickType":0,
            "strText":"天啊123收快递费的撒娇",
            "strTextColor":"#000000",
            "strTextSize":22
        }
    ]
}
	 */
  @WXComponentProp(name = "value")
  public void setText(String value){
    Log.i("RichText", "value=="+value);
    
    try {
    	  Gson gson = new Gson();
    	  MyRichJson mMyRichJson = gson.fromJson(value, MyRichJson.class);
    	    if(mMyRichJson!=null && mMyRichJson.getMyrichvalue()!=null&& mMyRichJson.getMyrichvalue().size()>0){
    	    	CustomLinkMovementMethod mCustomLinkMovementMethod = (CustomLinkMovementMethod) CustomLinkMovementMethod.getInstance();
				mCustomLinkMovementMethod.setOnTextClickListener(new CustomLinkMovementMethod.TextClickedListener() {
					@Override
					public void onTextClicked() {
					}
				});
				((TextView)getRealView()).setMovementMethod(mCustomLinkMovementMethod);
				
				SpannableString builder = new SpannableString(mMyRichJson.getContent());
				int start = 0;
	    		int end = 0;
    	    	for(int i=0;i<mMyRichJson.getMyrichvalue().size();i++){
    	    		MyRich myRichbean = mMyRichJson.getMyrichvalue().get(i);
    				
//    				ClickableSpan click_span = new ClickableSpan() {
//    					@Override
//    					public void onClick(View widget) {
//    						 
//    					}
//    					@Override
//    					public void updateDrawState(TextPaint ds) {
//    						super.updateDrawState(ds);
//    						// 设置没有下划线
//    						ds.setUnderlineText(false);
//    					}
//    				};
//    				builder.setSpan(click_span, 0, callbean.getUserName().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	    		Log.i("RichText", "i=="+i+";"+myRichbean.getStrText());
    	    		if(i==0){
    	    			start = 0;
    	    			end = myRichbean.getStrText().length();
    	    		}else{
    	    			start = end;
    	    			end = end + myRichbean.getStrText().length();
    	    		}
    	    		
    	    		builder.setSpan(new ForegroundColorSpan(Color.parseColor(myRichbean.getStrTextColor())), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    	    		builder.setSpan(new AbsoluteSizeSpan(myRichbean.getStrTextSize(), true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    	    	
    	    	}
    	    	((TextView)getRealView()).setText(builder);
//    	    	((TextView)getRealView()).setLinkTextColor(getContext().getResources().getIntArray(R.array.common_top_title_bar_bg_color)[getSkinType()]);
//				((TextView)getRealView()).setTextColor(getContext().getResources().getIntArray(R.array.txt_day_black_night_greywhite_color)[getSkinType()]);
				((TextView)getRealView()).setFocusable(false);
				((TextView)getRealView()).setLongClickable(false);
    	    }else{
    	    	((TextView)getRealView()).setText(value);
    	    }
	} catch (Exception e) {
		((TextView)getRealView()).setText(value);
	}
    
//    ((TextView)getRealView()).setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
  }
  
  class MyRichJson{
	  List<MyRich> myrichvalue;
	  String content;
	  
 
	public List<MyRich> getMyrichvalue() {
		return myrichvalue;
	}
	public void setMyrichvalue(List<MyRich> myrichvalue) {
		this.myrichvalue = myrichvalue;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	  
	  
  }
  
   class MyRich{
	  int onClickType;//0,
	  String strText;//从惺惺惜惺惺",
	  String strTextColor;//#000000",
	  int strTextSize;//:22
	  
	public int getOnClickType() {
		return onClickType;
	}
	public void setOnClickType(int onClickType) {
		this.onClickType = onClickType;
	}
	public String getStrText() {
		return strText;
	}
	public void setStrText(String strText) {
		this.strText = strText;
	}
	public String getStrTextColor() {
		return strTextColor;
	}
	public void setStrTextColor(String strTextColor) {
		this.strTextColor = strTextColor;
	}
	public int getStrTextSize() {
		return strTextSize;
	}
	public void setStrTextSize(int strTextSize) {
		this.strTextSize = strTextSize;
	}
	  
	  
   }
}