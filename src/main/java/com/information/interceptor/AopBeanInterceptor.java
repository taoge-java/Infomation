package com.information.interceptor;

import java.lang.reflect.Field;
import com.information.annotation.AopBean;
import com.information.config.AopBeanPlugin;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
/**
 * aop注入拦截器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月24日下午9:18:12
 */
public class AopBeanInterceptor implements Interceptor{

	private static final Log LOG=Log.getLog(AopBeanInterceptor.class);
	
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(AopBean.class)){
				bean=AopBeanPlugin.beanMap.get(field.getName());
			}else{
				continue ;
		    }
			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(controller, bean);
					LOG.info("Creating AOP:"+bean+":+"+bean.getClass().getPackage().getName());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		inv.invoke();
	}
}
