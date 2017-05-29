package com.information.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
/**
 * 手动数据库事物管理
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月8日 上午8:36:54
 */
public class TransactionServices {
	
	private Config config=null;
	private Connection conn=null;
	Boolean autoCommit=null;
	
	/**
	 * 开启事物
	 */
	@SuppressWarnings("unused")
	private void startTransaction(){
		config=DbKit.getConfig();
		conn=config.getThreadLocalConnection();
		try{
			if(conn!=null){
				if(conn.getTransactionIsolation()<config.getTransactionLevel()){
					conn.setTransactionIsolation(config.getTransactionLevel());//设置事物隔离级别
					return;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn=config.getConnection();
			autoCommit=conn.getAutoCommit();
			conn.setTransactionIsolation(config.getTransactionLevel());
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 提交事物
	 */
	public void commit(){
		if(conn!=null){
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 事物回滚
	 */
	public void rollback(){
		if(conn!=null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭事物
	 */
	public void close(){
		if(conn!=null){
			if(autoCommit!=null){
				try {
					conn.setAutoCommit(autoCommit);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
