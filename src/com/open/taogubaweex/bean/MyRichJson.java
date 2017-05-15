/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-5-12下午4:36:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.taogubaweex.bean;

import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-5-12下午4:36:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MyRichJson {
	/**
	{
	    "content":"1490952336000婷婷春蕾天啊123收快递费的撒娇",
	    "gravity":"center",
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
	List<MyRich> myrichvalue;
	String content;
	String gravity;// left,
	String tagTextColor;// 股票主题颜色
	int lines;
	List<Object> params;
	String  methodName;//反射原生方法
	String moduleName;//反射原生module
	
	

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

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

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public String getTagTextColor() {
		return tagTextColor;
	}

	public void setTagTextColor(String tagTextColor) {
		this.tagTextColor = tagTextColor;
	}
}
