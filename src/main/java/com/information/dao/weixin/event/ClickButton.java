package com.information.dao.weixin.event;

import com.information.dao.weixin.base.BaseButton;

/**
 * click按钮菜单
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日19:43
 */
public class ClickButton extends BaseButton{

	private String key;//菜单KEY值，用于消息接口推送，不超过128字节

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
