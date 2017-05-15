/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-4-17下午2:34:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.component;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.open.taogubaweex.bean.MyRich;
import com.open.taogubaweex.bean.MyRichJson;
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
	
  @WXComponentProp(name = "value")
  public void setText(String value){
    Log.i("RichText", "value=="+value);
    
    try {
    	  Gson gson = new Gson();
    	  MyRichJson mMyRichJson = gson.fromJson(value, MyRichJson.class);
    	    if(mMyRichJson!=null && mMyRichJson.getMyrichvalue()!=null&& mMyRichJson.getMyrichvalue().size()>0){
    	    	try {
    	    		CustomLinkMovementMethod mCustomLinkMovementMethod = (CustomLinkMovementMethod) CustomLinkMovementMethod.getInstance();
    				mCustomLinkMovementMethod.setOnTextClickListener(new CustomLinkMovementMethod.TextClickedListener() {
    					@Override
    					public void onTextClicked() {
    						Log.e("CustomLinkMovementMethod", "onTextClicked");
    					}
    				});
    				((TextView)getRealView()).setMovementMethod(mCustomLinkMovementMethod);
    				
    				SpannableString spannable = new SpannableString(mMyRichJson.getContent().replace("[tag]", "").replace("[/tag]", "")
    						.replace("[theme]", "").replace("[/theme]", ""));
    				int start = 0;
    				int end = 0;
    				int preend = 0;
    				final String tagreplace ="[tag][/tag]";
    				final String themereplace ="[theme][/theme]";
    				String tag = "\\[tag\\](.*?)\\[\\/tag\\]|\\[theme\\](.*?)\\[\\/theme\\]";
        	    	for(int i=0;i<mMyRichJson.getMyrichvalue().size();i++){
        	    		MyRich myRichbean = mMyRichJson.getMyrichvalue().get(i);
        	    		Matcher matcher = Pattern.compile(tag).matcher(myRichbean.getStrText());
        	    		Log.i("RichText", "i=="+i+";"+myRichbean.getStrText());
        	    		
        	    		int find = 0;
    					int tagfind = 0;
    					int themefind = 0;
    					while (matcher.find()) {
    						int pstart = 0;
    						int pend = 0;
    						String tagContent = "";
    						try {
    							tagContent = matcher.group().toString();
    							pstart = matcher.start();
    							pend = matcher.end();
    						} catch (Exception e) {
    							
    						}
    						
    						if (find == 0) {
    							preend += end;
    							if (i == 0) {
    								start = 0;
    							} else {
    								start = end;
    							}
    						} 
    						end = preend + pstart-tagfind*tagreplace.length()-themefind*themereplace.length();
    						spannable.setSpan(new ForegroundColorSpan(Color.parseColor(myRichbean.getStrTextColor())), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    						spannable.setSpan(new AbsoluteSizeSpan(myRichbean.getStrTextSize(),true ), start, end,
    								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    						if(tagContent.contains("[tag]")){
    							tagfind ++;
    						}
    						
    						if(tagContent.contains("[theme]")){
    							themefind++;
    						}
    						find++;
    						// 正则start --- end
    						start = end;
    						end = preend + pend -tagfind*tagreplace.length()-themefind*themereplace.length();
    						spannable.setSpan(new ForegroundColorSpan(Color.parseColor(mMyRichJson.getTagTextColor())), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    						spannable.setSpan(new AbsoluteSizeSpan(myRichbean.getStrTextSize(),true ), start, end,
    								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    						ClickableSpan click_span = new ClickableSpan() {
    	    					@Override
    	    					public void onClick(View widget) {
    	    						 Log.e("ClickableSpan", "onClick");
    	    					}
    	    					@Override
    	    					public void updateDrawState(TextPaint ds) {
    	    						super.updateDrawState(ds);
    	    						// 设置没有下划线
    	    						ds.setUnderlineText(false);
    	    					}
    	    				};
    	    				spannable.setSpan(click_span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    					}
        	    		
    					if(find>0){
    						// 剩余长度
    						start = end;
    						end = preend + myRichbean.getStrText().length() -tagfind*tagreplace.length()-themefind*themereplace.length();
    						spannable.setSpan(new ForegroundColorSpan(Color.parseColor(myRichbean.getStrTextColor())), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    						spannable.setSpan(new AbsoluteSizeSpan(myRichbean.getStrTextSize(),true ),start, end,
    								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    					}
    					
    					// 没有正则
    					if (find == 0) {
    						if (i == 0) {
    							start = 0;
    							end = myRichbean.getStrText().length();
    						} else {
    							start = end;
    							end = end + myRichbean.getStrText().length();
    						}
    						spannable.setSpan(new ForegroundColorSpan(Color.parseColor(myRichbean.getStrTextColor())), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    						spannable.setSpan(new AbsoluteSizeSpan(myRichbean.getStrTextSize(),true ), start, end,
    								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    					}
        	    	}
        	    	((TextView)getRealView()).setText(spannable);
        	    	((TextView)getRealView()).setLinkTextColor(Color.parseColor(mMyRichJson.getTagTextColor()));
//    				((TextView)getRealView()).setTextColor(getContext().getResources().getIntArray(R.array.txt_day_black_night_greywhite_color)[getSkinType()]);
    				((TextView)getRealView()).setFocusable(false);
    				((TextView)getRealView()).setLongClickable(false);
    				if(mMyRichJson.getGravity()!=null && mMyRichJson.getGravity().length()>0){
    					 if("center".equals(mMyRichJson.getGravity())){
    						 ((TextView)getRealView()).setGravity(Gravity.CENTER);
    					 }else  if("left".equals(mMyRichJson.getGravity())){
    						 ((TextView)getRealView()).setGravity(Gravity.LEFT);
    					 }else  if("right".equals(mMyRichJson.getGravity())){
    						 ((TextView)getRealView()).setGravity(Gravity.RIGHT);
    					 }
    				}else{
    					((TextView)getRealView()).setGravity(Gravity.LEFT);
    				}
    				if(mMyRichJson.getLines()!=0){
    					((TextView)getRealView()).setMaxLines(mMyRichJson.getLines());
    					((TextView)getRealView()).setEllipsize(TruncateAt.END);
    				}
				} catch (Exception e) {
					// TODO: handle exception
					((TextView)getRealView()).setText(mMyRichJson.getContent());
					if(mMyRichJson.getLines()!=0){
    					((TextView)getRealView()).setMaxLines(mMyRichJson.getLines());
    					((TextView)getRealView()).setEllipsize(TruncateAt.END);
    				}
				}
    	    	
    	    }else{
    	    	((TextView)getRealView()).setText(value);
    	    }
    	   
	} catch (Exception e) {
		((TextView)getRealView()).setText(value);
	}
    
//    ((TextView)getRealView()).setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
  }
  
   
}