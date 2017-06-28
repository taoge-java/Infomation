package com.information.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.information.spring.SpringBeanManger;
import com.jfinal.log.Log;
/**
 * 系统监听器,初始化加载数据
 * @author Administrator
 *
 */
public class WebContextListener implements ServletContextListener{
	
	Log LOG=Log.getLog(WebContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("spring-context start........");
		ApplicationContext app=new ClassPathXmlApplicationContext("spring-context.xml");
		SpringBeanManger.initContext(app);
		LOG.info("spring-context.xml加载完成.......");
	}

}
