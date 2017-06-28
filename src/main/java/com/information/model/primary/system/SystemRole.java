package com.information.model.primary.system;
import com.information.model.primary.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * 角色表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:55:02
 */
@TableBind(tableName="system_role")
public class SystemRole extends BaseModel<SystemRole>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821093056423411249L;
	
	public static final SystemRole dao=new SystemRole();


}
