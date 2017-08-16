package com.information.interceptor;

import java.lang.reflect.Field;

import org.osgl.inject.Genie;

import com.information.annotation.AopBean;
import com.information.spring.AopManger;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
/**
 * aop注入拦截器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月24日下午9:18:12
 */
public class AopInterceptor implements Interceptor{
	
	private static final Genie genie = Genie.create();
	
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(AopBean.class)){
				bean = AopManger.beanMap.get(field.getName());
				Class<?> cla = genie.get(field.getType()).getClass();
				for(Field f : cla.getDeclaredFields()){
					Object serviceBean = null;
					if(f.isAnnotationPresent(AopBean.class)){
						serviceBean = AopManger.beanMap.get(f.getName());
					}
					if(serviceBean != null){
						try {
							f.setAccessible(true);
							f.set(bean, serviceBean);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new NullPointerException();
						}
					}
				}
			}else{
				continue ;
		    }
			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(controller, bean);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		inv.invoke();
	}
}
