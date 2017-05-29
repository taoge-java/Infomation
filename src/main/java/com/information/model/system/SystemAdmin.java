package com.information.model.system;
import com.information.model.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * 系统管理员表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:55:42
 */
@TableBind(tableName="system_admin")
public class SystemAdmin extends BaseModel<SystemAdmin>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2457995147027662584L;
	
	public static final SystemAdmin dao=new SystemAdmin();


}
