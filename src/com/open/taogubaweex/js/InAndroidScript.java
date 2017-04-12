/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-4-12上午9:52:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.js;


import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-4-12上午9:52:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class InAndroidScript {
	public static final String TAG = InAndroidScript.class.getSimpleName();
	Context mContext;
	Handler mHandler = new Handler(){
		
	};
	
	public InAndroidScript(Context mContext) {
		this.mContext = mContext;
	}
	
	@JavascriptInterface
	public void toast(String toast){
		Log.d(TAG, "toast=="+toast);
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mContext, "toast", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
