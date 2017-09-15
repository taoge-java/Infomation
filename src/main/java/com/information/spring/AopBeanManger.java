package com.information.spring;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AopBeanManger {
	
	private static final AopBeanManger me = new AopBeanManger();
	
	private static  ConcurrentMap<String,Object> beanMap = new ConcurrentHashMap<String,Object>();
	
    private AopBeanManger(){
		
	}
    
	public static AopBeanManger getInstance(){
		return me;
	}
	
	public void put(String keyName,Object object){
		beanMap.put(keyName, object);
	}
	
	public Object get(String keyName){
		return beanMap.get(keyName);
	}
}
