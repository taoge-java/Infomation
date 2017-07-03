package com.information.utils;

public class ClassUtil {

	private Object object;
	
	public ClassUtil(Class<?> target){
		this.object=target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(){
		return (T) object;
	}
	
	public static Class<?> forName(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ClassUtil getInstance(Class<?> type){
		return new ClassUtil(type);
	}
}
