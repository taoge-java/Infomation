package com.information.config;

import java.util.ArrayList;
import java.util.List;

import com.information.annotation.Aop;
import com.information.spring.AopManger;
import com.information.utils.PackageUtil;
import com.information.utils.StrKit;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.IPlugin;

public class BeanPlugin<T> implements IPlugin{
    private List<String> beanList=new ArrayList<String>();
	
	@SuppressWarnings("rawtypes")
	private List<Class> excludeClasses=new ArrayList<Class>();
	
	private boolean autoScan;
	
	public BeanPlugin<T> setPackageName(String... packageName){
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
	public BeanPlugin setAutoScan(boolean autoScan) {
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
			 if(target.isAnnotationPresent(Aop.class)){
				 Object object= Duang.duang(target.getName(),target);
				 String value=target.getAnnotation(Aop.class).value();
				 String simpleName=target.getSimpleName().substring(1, target.getSimpleName().length());
				 String key=StrKit.toLowerCaseFirst(target.getSimpleName())+simpleName;
				 if(StrKit.isNotEmpoty(value)){
					 AopManger.beanMap.put(value, object);
				 }else{
					 AopManger.beanMap.put(key, object);
				 }
			 }
			 continue;
		 }
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
	
	public BeanPlugin
	<T> addExcludeClasses(Class<?>... clazzes) {
        if (clazzes != null) {
            for (Class<?> clazz : clazzes) {
                excludeClasses.add(clazz);
            }
        }
        return this;
    }
}
