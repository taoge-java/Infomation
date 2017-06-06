package com.information.model.weixin;

import com.information.model.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;
/**
 * 微信用户列表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月4日 下午7:14:23
 */
@TableBind(tableName="weixin_user")
public class WeiXinUser extends BaseModel<WeiXinUser>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final WeiXinUser dao=new WeiXinUser();
}
