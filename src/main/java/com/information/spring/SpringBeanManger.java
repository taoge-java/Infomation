package com.information.spring;

import org.springframework.context.ApplicationContext;
/**
 * Spring手动管理bean
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月8日 上午8:37:45
 */
public class SpringBeanManger {
	
	private static ApplicationContext context;
	

	public static void initContext(ApplicationContext ctx){
		context=ctx;
	}
	
	public static Object getBean(String name){
		return context.getBean(name);
	}

	public static ApplicationContext getContext() {
		return context;
	}
	
	public static <T> T getBean(String name, Class<T> cls) {
		return context.getBean(name, cls);
	}
}
