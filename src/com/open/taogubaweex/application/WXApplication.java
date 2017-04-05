package com.open.taogubaweex.application;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/3/3
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.open.taogubaweex.adapter.ImageAdapter;
import com.open.taogubaweex.adapter.WXHttpAdapter;
import com.open.taogubaweex.component.MyInput;
import com.open.taogubaweex.component.RichText;
import com.open.taogubaweex.module.WXActionSheetModule;
import com.open.taogubaweex.module.WXEventModule;
import com.open.taogubaweex.module.WeexModalUIModule;
import com.open.taogubaweex.module.WeexModule;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * 注意要在Manifest中启用
 * 参考manifest，否则会抛出ExceptionInInitializerError
 * 要实现ImageAdapter 否则图片不能下载
 * gradle 中一定要添加一些依赖，否则初始化会失败。
 * compile 'com.android.support:recyclerview-v7:23.1.1'
 * compile 'com.android.support:support-v4:23.1.1'
 * compile 'com.android.support:appcompat-v7:23.1.1'
 * compile 'com.alibaba:fastjson:1.1.45'
 */
public class WXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"zksrg6fpR18GjAsv0eHPs4Kz-gzGzoHsz","XfkYkvCvsJ1FkhEqzdTsMnNC");
     // host 表示debug server的ip或域名
        
//        WXEnvironment.sRemoteDebugMode = true;
//        WXEnvironment.sRemoteDebugProxyUrl = "ws://" + WeexUtils.IP + ":8088/debugProxy/native";
        
        InitConfig config=new InitConfig.Builder().setHttpAdapter(new WXHttpAdapter()).setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);
        try {
			WXSDKEngine.registerModule("weexModule", WeexModule.class);
			WXSDKEngine.registerModule("weexModalUIModule", WeexModalUIModule.class);
			WXSDKEngine.registerModule("myModule", WXEventModule.class);
			WXSDKEngine.registerModule("actionSheet", WXActionSheetModule.class);
			 // 注册 webview module
//			WXSDKEngine.registerModule("webview", WXWebViewModule.class);
	        // 注册 webview 组件
//	        registerComponent("web", WXWeb.class);
			
			WXSDKEngine.registerComponent("myinput", MyInput.class);
			WXSDKEngine.registerComponent("richtext",RichText.class);
		} catch (WXException e) {
			e.printStackTrace();
		}
        
//        WXSDKEngine.addCustomOptions("appName", "WXSample");
//        WXSDKEngine.addCustomOptions("appGroup", "WXApp");
//        WXSDKEngine.initialize(this,
//            new InitConfig.Builder()
//                .setImgAdapter(new ImageAdapter())
//                .build()
//        );

//        Fresco.initialize(this);
//        AppConfig.init(this);
//        ExtensionManager.registerComponents(AppConfig.getComponents());
//        ExtensionManager.registerModules(AppConfig.getModules());
        
    
    }
}