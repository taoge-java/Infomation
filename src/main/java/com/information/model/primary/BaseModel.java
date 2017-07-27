package com.information.model.primary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Model;

import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * model基类
 * @author zengjintao
 * 2016年10月30日下午 18:32:28
 * @param <M>
 */

@SuppressWarnings("serial")
public class BaseModel<M extends Model<M>> extends Model<M>{

	public void getParamters(Map<String,String[]> map){
		
		String modelName=getClass().getSimpleName();
		/**
		 * 遍历集合
		 */
	    for(Entry<String, String[]> e: map.entrySet()){
	    	//获得map的key值
			String paraKey=e.getKey();
			if(paraKey.toLowerCase().startsWith(modelName.toLowerCase()+".")){
				if(e.getValue().length == 1) {
					set(paraKey.substring(paraKey.indexOf(".")+1), e.getValue()[0]);
				}else if(e.getValue().length > 1) {
					set(paraKey.substring(paraKey.indexOf(".")+1), StringUtils.join(e.getValue(), ","));
				}
			}
		}
	}
	
	/**
	 * model删除方法
	 * @param sql
	 * @param paras
	 * @return
	 */
	public boolean delete(String sql,Object...paras){
		Config config=DbKit.getConfig();
		return execute(config,sql,paras);
			
	}
	
	/**
	 * model update方法
	 * @param sql
	 * @param paras
	 * @return
	 */
	public boolean update(String sql,Object...paras){
		Config config=DbKit.getConfig();
		return execute(config,sql,paras);
	}
	
	private boolean execute(Config config,String sql,Object...paras){
		Connection conn;
		try {
			conn = config.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				statement.setObject(i+1,paras[i]);
			}
			statement.executeUpdate();
			config.close(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
