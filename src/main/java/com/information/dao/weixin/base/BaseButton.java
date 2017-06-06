package com.information.dao.weixin.base;
/**
 * 菜单按钮父类
 * @author zengjintao
 * @version 1.0
 * 2017年4月8日上午9:55
 */
public class BaseButton {

	private String name;
	
	private String type;
	
	private BaseButton sub_button[];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BaseButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(BaseButton[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
