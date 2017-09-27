package com.test;
import javax.sql.DataSource;
import com.information.utils.StrKit;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Plugin {

	private String jdbcUrl;
	
	private String user;
	
	private String password;
	
	private String driverClass="com.mysql.jdbc.Driver";
	
	private int maxPoolSize = 100;//最大连接数
	
	private int minPoolSize = 10;//最小连接数
	
	private int initialPoolSize = 10;//初始化连接数
	
	private int maxIdleTime = 20;//最大空闲时间，20秒内未使用则连接被丢弃
	
	private int acquireIncrement = 2;//当连接池中的连接耗尽的时候c3p0一次同时获取的连接数
	
	public C3p0Plugin setAcquireIncrement(int acquireIncrement) {
		if(StrKit.isEmpoty(acquireIncrement))
			throw new IllegalArgumentException("acquireIncrement must more than 0.");
		this.acquireIncrement = acquireIncrement;
		return this;
	}

	
	private  ComboPooledDataSource dataSource;  
	
	public int getAcquireIncrement() {
		return acquireIncrement;
	}


	public C3p0Plugin setDriverClass(String driverClass) {
		if(StrKit.isEmpoty(driverClass))
			throw new IllegalArgumentException("driverClass can not be blank.");
		this.driverClass = driverClass;
		return this;
	}

	public C3p0Plugin setMaxPoolSize(int maxPoolSize) {
		if (maxPoolSize < 1)
			throw new IllegalArgumentException("maxPoolSize must more than 0.");
		this.maxPoolSize = maxPoolSize;
		return this;
	}
	
	public C3p0Plugin setMinPoolSize(int minPoolSize) {
		if (minPoolSize < 1)
			throw new IllegalArgumentException("minPoolSize must more than 0.");
		this.minPoolSize = minPoolSize;
		return this;
	}
	
	public C3p0Plugin setInitialPoolSize(int initialPoolSize) {
		if (initialPoolSize < 1)
			throw new IllegalArgumentException("initialPoolSize must more than 0.");
		this.initialPoolSize = initialPoolSize;
		return this;
	}
	
	public C3p0Plugin setMaxIdleTime(int maxIdleTime) {
		if (maxIdleTime < 1)
			throw new IllegalArgumentException("maxIdleTime must more than 0.");
		this.maxIdleTime = maxIdleTime;
		return this;
	}

	public C3p0Plugin(String jdbcUrl,String user,String password){
		this.jdbcUrl=jdbcUrl;
		this.user=user;
		this.password=password;
	}


	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DataSource getDataSource() {
		dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setInitialPoolSize(initialPoolSize);
		dataSource.setMaxIdleTime(maxIdleTime);
		try {
			dataSource.setDriverClass(driverClass);
			return dataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setDataSource(ComboPooledDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public C3p0Plugin(String jdbcUrl,String user,String password,String driverClass){
		this.jdbcUrl=jdbcUrl;
		this.user=user;
		this.password=password;
		this.driverClass=(driverClass!=null?driverClass:this.driverClass);
	}
	
	public DataSource getConnection(){
		dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setInitialPoolSize(initialPoolSize);
		dataSource.setMaxIdleTime(maxIdleTime);
		try {
			dataSource.setDriverClass(driverClass);
			return dataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close(){
		if(dataSource!=null){
			dataSource.close();
		}
	}
}
