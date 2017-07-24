package com.information.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.information.annotation.AopConfig;
import com.information.annotation.Service;
import com.information.utils.PackageUtil;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.IPlugin;

/**
 * aop自动注入插件
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月23日下午1:39:11
 */
public class BeanFactoryPlugin<T> implements IPlugin{

	private List<String> beanList=new ArrayList<String>();
	
	private static final Map<String,Object> map=new HashMap<String,Object>();
	@SuppressWarnings("rawtypes")
	private List<Class> excludeClasses=new ArrayList<Class>();
	
	private boolean autoScan;
	
	public BeanFactoryPlugin<T> setPackageName(String... packageName){
		if(packageName.length==0||packageName==null)
			throw new NullPointerException("packageName can not be null");
		for(String pack:packageName){
			beanList.add(pack);
		}
		return this;
	}
	
	public boolean isAutoScan() {
		return autoScan;
	}

	@SuppressWarnings("rawtypes")
	public BeanFactoryPlugin setAutoScan(boolean autoScan) {
		this.autoScan = autoScan;
		return this;
	}

	@Override
	public boolean start() {
		 List<Class<? extends T>> targetClass=PackageUtil.scanPackage(beanList,"WEB-INF/classes/",true);
		 for(Class<? extends T> target:targetClass){
			 if(excludeClasses.contains(target)){
				 continue;
			 }
			 if(target.isAnnotationPresent(Service.class)){
				 Object object= Duang.duang(target.getName(),target);
				 map.put(target.getAnnotation(Service.class).value(), object);
			 }else if(target.isAnnotationPresent(AopConfig.class)){
				 Field[] field= target.getDeclaredFields();
				 for(Field f:field){
					 if(f.isAnnotationPresent(AopConfig.class)){
						try {
							f.setAccessible(true);
							f.set(f.getName(), map.get(f.getName()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					 }
					 continue;
			      }
			 }
		 }
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
	
	
	public BeanFactoryPlugin<T> addExcludeClasses(Class<?>... clazzes) {
        if (clazzes != null) {
            for (Class<?> clazz : clazzes) {
                excludeClasses.add(clazz);
            }
        }
        return this;
    }
	
	public Object getBean(String keyName){
		return keyName;
	}
	
//	 if(target.isAnnotationPresent(Service.class)){
//	 Object object= Duang.duang(target.getName(),target);
//	 map.put(object.getClass().getName(), object);
//}
//Field[] field= target.getDeclaredFields();
//for(Field f:field){
//	 if(f.isAnnotationPresent(AopConfig.class)){
//		Object object= Duang.duang(target.getName(),target);
//	//	String keyName=f.getName();
//		try {
//			f.setAccessible(true);
//			System.out.println(f.getClass());
//			System.out.println(f.getName());
//			f.set(f.getName(), object);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//map.put(object.getClass().getName(), object);
//	 }
//	 continue;
//}

}
