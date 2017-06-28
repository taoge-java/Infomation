package com.information.model.primary.system;
import com.information.model.primary.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * 系统操作权限表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:52:27
 */
@TableBind(tableName="system_oper")
public class SystemOper extends BaseModel<SystemOper>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final  SystemOper dao=new SystemOper();
}
