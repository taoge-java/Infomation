package com.information.spring;

import java.util.HashMap;
import java.util.Map;



/**
 * 对象工厂
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月28日上午9:28:51
 */
public class BeanFactory {

    private static final Map<String,Object> map=new HashMap<String,Object>();
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(String key,Class<T> cla){
		Object target=map.get(key);
		if(target==null){
			try {
				target=cla.newInstance();
				map.put(key, target);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return (T)target;
	}
}
