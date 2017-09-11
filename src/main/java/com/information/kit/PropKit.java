package com.information.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;


public class PropKit {
	
	private  Properties properties = new Properties();
	
	private String fileName;
	
	public PropKit(Properties properties){
		this.properties = properties;
	}
	
	public PropKit(String fileName){
		this.fileName = fileName;
		loadProperties();
	}
	
	private PropKit loadProperties() {
		getProperties();
		return this;
	}

	public PropKit(File file,String encoding){
		loadFileProperties(file,encoding);
	}
	
	private PropKit loadFileProperties(File file,String encoding) {
		 getProperties(file,encoding);
		 return this;
	}

	private Properties getProperties(File file,String encoding){
		InputStream in;
		try {
			in = new FileInputStream(file);
			properties.load(new InputStreamReader(in, encoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public  Properties getProperties(String fileName,String encoding){
		return getProperties();
	}
	
	private  Properties getProperties(){
		InputStream in;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);	
			properties.load(new InputStreamReader(in,"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	@SuppressWarnings("unchecked")
	public Enumeration<String> getAllKey(){
		return (Enumeration<String>) properties.propertyNames();
	}
	
	public String get(String key){
		return properties.getProperty(key);
	}
	
	public String get(String key,String defaultValue){
		if(get(key)!=null){
			return get(key);
		}
		return defaultValue;
	}
	
	public Integer getInt(String key){
		return getInt(key,null);
	}
	
	public Integer getInt(String key,Integer defaultValue){
		String value=get(key);
		if(key!=null){
			return Integer.parseInt(value.trim());
		}
		return defaultValue;
	}

	public long getLong(String key){
		return getLong(key,null);
	}
	
	
	public long getLong(String key,Long defaultValue){
		String value=get(key);
		if(key!=null){
			return Long.parseLong(value.trim());
		}
		return defaultValue;
	}
	
	public Boolean getBoolean(String key){
		return getBoolean(key,null);
	}
	
	public Boolean getBoolean(String key,Boolean defaultValue){
		String value=get(key);
		if(value!=null){
			value=value.toLowerCase();
			if(value.equals("true")){
				return true;
			}else if(value.equals("false")){
				return false;
			}
			throw new RuntimeException("The value can not parse to Boolean : " + value);
		}
		return defaultValue;
	}
	
	public Properties getPropertie(){
		return properties;
	}
}
