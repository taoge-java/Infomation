package com.information.model.system;
import com.information.model.BaseModel;
import com.jfinal.ext.plugin.tablebind.TableBind;
/**
 * 系统日志表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:55:23
 */
@TableBind(tableName="system_log")
public class SystemLog extends BaseModel<SystemLog>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemLog dao=new SystemLog();

}
