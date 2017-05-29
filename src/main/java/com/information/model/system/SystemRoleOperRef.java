package com.information.model.system;

import com.information.model.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * 角色操作关联表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:53:46
 */
@TableBind(tableName="system_role_oper_ref")
public class SystemRoleOperRef extends BaseModel<SystemRoleOperRef>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemRoleOperRef dao=new SystemRoleOperRef();

}
