package com.information.model.primary.system;
import com.information.model.primary.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * 系统菜单表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:51:20
 */
@TableBind(tableName="system_menu")
public class SystemMenu extends BaseModel<SystemMenu>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemMenu dao=new SystemMenu();

}
