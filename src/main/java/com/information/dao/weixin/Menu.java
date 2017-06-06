package com.information.dao.weixin;

import com.information.dao.weixin.base.BaseButton;

/**
 * 微信公众号菜单
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日19:48
 */
public class Menu {

	private BaseButton button[];

	public BaseButton[] getButton() {
		return button;
	}

	public void setButton(BaseButton[] button) {
		this.button = button;
	}

}
