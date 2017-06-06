package com.information.dao.weixin.event;

import com.information.dao.weixin.base.BaseButton;

/**
 * View按钮菜单
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日19:43
 */
public class ViewButton extends BaseButton{

	private String url;//网页链接，用户点击菜单可打开链接，不超过256字节

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
