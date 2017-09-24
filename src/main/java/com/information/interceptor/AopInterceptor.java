package com.information.interceptor;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.information.annotation.AopBean;
import com.information.spring.AopBeanManger;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
/**
 * aop对象注入拦截器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月24日下午9:18:12
 */
public class AopInterceptor implements Interceptor{
	
	private  ApplicationContext ctx;
	
	private AopBeanManger aopManger = AopBeanManger.getInstance();
	
	public AopInterceptor(ApplicationContext ctx){
		this.ctx = ctx;
	}
	
	public AopInterceptor(){
		
	}
	
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		//controller层aop的自动注入
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(AopBean.class)){
				bean = aopManger.get(field.getName());
			}else if(field.isAnnotationPresent(Autowired.class) && ctx != null){
				bean = ctx.getBean(field.getName());
			}else{
				continue ;
		    }
			try {
				initServiceBean(bean,field);
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
	
	/**
	 * service层 bean的自动注入
	 * @param bean
	 * @param field
	 */
	private  void initServiceBean(Object bean,Field field){
		Class<?> cla = field.getType();
		//service层aop的自动注入
		for(Field f : cla.getDeclaredFields()){
			Object serviceBean = null;
			if(f.isAnnotationPresent(AopBean.class)){
				serviceBean = aopManger.get(f.getName());
			}else if(f.isAnnotationPresent(Autowired.class)){
				serviceBean = ctx.getBean(f.getName());
			}
			if(serviceBean != null){
				try {
					f.setAccessible(true);
					f.set(bean, serviceBean);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new NullPointerException("Field can not be null");
				}
			}
		}
	}
}
