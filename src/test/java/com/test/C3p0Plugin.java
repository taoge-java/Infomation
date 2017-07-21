package com.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.information.utils.StrKit;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * c3p0数据库连接池
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月28日上午9:25:41
 */
public class C3p0Plugin {
	
	private int minPoolSize=10;//最小连接数
	
	private int maxPoolSize=100;//最大连接数
	
	private int maxIdle=10;// 连接池中最多可空闲maxIdle个连接 

	private int maxIdleTime=10;//maxIdleTime
	
	private String user;//用户名
	
	private String password;//密码
	
	private String jdbcUrl;
	
	private String driverClass="com.mysql.jdbc.Driver";
	
	private ComboPooledDataSource dataSource;
	
	public C3p0Plugin setMinPoolSize(int minPoolSize){
		if(minPoolSize<1)
			throw new IllegalArgumentException("minPoolSize must more than 0.");
		this.minPoolSize=minPoolSize;
		return this;
	}
	
	public C3p0Plugin setMaxPoolSize(int maxPoolSize){
		if(maxPoolSize<1)
			throw new IllegalArgumentException("maxPoolSize must more than 0.");
		this.maxPoolSize=maxPoolSize;
		return this;
	}
	
	public C3p0Plugin setMaxIdle(int maxIdle){
		if(maxIdle<1)
			throw new IllegalArgumentException("maxPoolSize must more than 0.");
		this.maxIdle=maxIdle;
		return this;
	}
	
	public C3p0Plugin setMaxIdleTime(int maxIdleTime){
		if(maxIdle<1)
			throw new IllegalArgumentException("maxIdleTime must more than 0.");
		this.maxIdleTime=maxIdleTime;
		return this;
	}
	
	public C3p0Plugin setDriverClass(int maxIdle){
		if(StrKit.isEmpoty(driverClass))
			throw new IllegalArgumentException("driverClass can not be null");
		this.maxIdle=maxIdle;
		return this;
	}
	
	public C3p0Plugin(String user,String password,String jdbcUrl){
		this.user=user;
		this.password=password;
		this.jdbcUrl=jdbcUrl;
	}
	
	public C3p0Plugin(String user,String password,String jdbcUrl,String driverClass){
		this.user=user;
		this.password=password;
		this.jdbcUrl=jdbcUrl;
		this.driverClass=driverClass!=null?driverClass:this.driverClass;
	}
	
	public Connection getConnection(){
		try {
			dataSource=new ComboPooledDataSource();
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setJdbcUrl(jdbcUrl);
			dataSource.setMaxIdleTime(maxIdleTime);
			dataSource.setMinPoolSize(minPoolSize);
			dataSource.setMinPoolSize(maxPoolSize);
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
		if(dataSource!=null)
			dataSource.close();
	}
}
