package com.information.spring;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AopManger {

    public static  ConcurrentMap<String,Object> beanMap=new ConcurrentHashMap<String,Object>();
	
	public void put(String keyName,Object object){
		beanMap.put(keyName, object);
	}
	
	public Object get(String keyName){
		return beanMap.get(keyName);
	}
}
