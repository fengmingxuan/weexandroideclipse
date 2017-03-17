package com.open.taogubaweex;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.open.taogubaweex.utils.WeexUtils;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

public class MainActivity extends  Activity implements IWXRenderListener {

    WXSDKInstance mWXSDKInstance;
	Map<String, Object> options = new HashMap<String, Object>();
	int themetype=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         * width 为-1 默认全屏，可以自己定制。
         * height =-1 默认全屏，可以自己定制。
         */
        options.put("themetype", themetype);
        mWXSDKInstance.renderByUrl("MyApplication",WeexUtils.HTTP+"://"+WeexUtils.IP+":"+WeexUtils.PORT+"/"+WeexUtils.MAIN_JS,null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
      
//        mWXSDKInstance.renderByUrl("MyApplication","http://192.168.1.15:8080/dist/weexbar/tabbar.js",null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
//        mWXSDKInstance.render("MyApplication", WXFileUtils.loadAsset("index.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    
    
        /**
		 * 
		 * WXSample 可以替换成自定义的字符串，针对埋点有效。 template 是.we transform 后的 js文件。 option
		 * 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。 jsonInitData 可以为空。 width
		 * 为-1 默认全屏，可以自己定制。 height =-1 默认全屏，可以自己定制。 main.we / main.vue
		 * 文件，也就是上面代码中的main.js文件中的this.$getConfig()来获取传进来的参数 module.exports = {
		 * data: { aa: '', bb: '', bundleUrl: '' }, methods: { // 获取 native的传参
		 * getOptions: function() { this.aa = this.$getConfig().aa; this.bb =
		 * this.$getConfig().bb; this.bundleUrl = this.$getConfig().bundleUrl; }
		 * } }
		 */
		// Map<String, Object> options = new HashMap<>();
		// options.put(WXSDKInstance.BUNDLE_URL, url); // 传递bundleUrl
		// options.put("aa", "aaa"); // 传递自定义参数 aa
		// options.put("bb", "ccc"); // 传递自定义参数 bb
		// mWXSDKInstance.render("MyApplication",
		// WXFileUtils.loadAsset("main.js", this), options, null, -1, -1,
		// WXRenderStrategy.APPEND_ASYNC);

		// mWXSDKInstance.renderByUrl("MyApplication","http://192.168.1.15:8080/dist/weexbar/tabbar.js",null,
		// null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
		// mWXSDKInstance.render("MyApplication",
		// WXFileUtils.loadAsset("index.js", this), null, null, -1, -1,
		// WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityResume();
			Log.d("MainActivity", "onResume");
			options.put("themetype", themetype);
			//callJS >>>> instanceId:1function:callJS tasks:[{"data":"1","type":2},{"data":"[{\"args\":[\"1\",{\"bundleUrl\":\"http://192.168.1.15:12580/dist/mainlist.js\",\"themetype\":1},true],\"method\":\"callback\"}]","type":3}]
			mWXSDKInstance.fireGlobalEventCallback("mainlist_text_day_night", options);
			
			//callJS >>>> instanceId:1function:callJS tasks:[{"data":"1","type":2},{"data":"[{\"args\":[\"mainlist_text_day_night_ref\",\"mainlist_text_day_night\",{\"bundleUrl\":\"http://192.168.1.15:12580/dist/mainlist.js\",\"themetype\":1},null],\"method\":\"fireEvent\"}]","type":3}]
//			c(mWXSDKInstance.getInstanceId(), "_root", "mainlist_text_day_night",options);
		}
    }

    @Override
    protected void onPause() {
        super.onPause();
        themetype = 1;
		super.onPause();
		if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityPause();
			Log.d("MainActivity", "onPause");
		}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
