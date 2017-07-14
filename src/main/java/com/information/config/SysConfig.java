package com.information.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.information.interceptor.IocInterceptor;
import com.information.interceptor.PermissionInterceptor;
import com.information.interceptor.ViewContextInterceptor;
import com.information.job.base.JobManger;
import com.information.listener.RedisListener;
import com.information.model.primary.BaseModel;
import com.information.model.slave.SlaveBaseModel;
import com.information.redis.SubClient;
import com.information.service.weixin.WeiXinService;
import com.information.spring.SpringBeanManger;
import com.information.spring.SpringPlugin;
import com.jfinal.aop.Duang;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.RenderingTimeHandler;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.VelocityRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import net.sf.json.JSONObject;
/**
 * Jfinal Aip引导式配置
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月8日 下午9:13:31
 */
@SuppressWarnings("unused")
public class SysConfig extends JFinalConfig{
	
	private WeiXinService weiXinService=Duang.duang(WeiXinService.class.getSimpleName(),WeiXinService.class);

	private Logger LOG=Logger.getLogger(SysConfig.class);
	
	public final static String BASE_VIEW="/WEB-INF/views";//页面存放路径
	
	public static String redisHost; // redis主机
	
	public static String channels;//redis订阅频道
	
	public static String redisPassword; // redis密码
	
	public static String resourceUpload;//文件上传路径
	
	public static String resourceDown;
	
	public static String cookie_name;
	
	public static String  weixinToken;
	
	@Override
	public void configConstant(Constants constants) {
		 constants.setDevMode(true);
		 constants.setViewType(ViewType.VELOCITY);
		 constants.setEncoding("utf-8");
		 JFinal.me().getConstants().setError404View(BASE_VIEW+"/common/404.vm");
		 JFinal.me().getConstants().setError500View(BASE_VIEW+"/common/500.vm");
		 PropKit.use("config.properties");//加载配置文件
		 redisPassword = PropKit.get("redis.password").trim();
		 redisHost = PropKit.get("redis.host").trim();
		 channels=PropKit.get("redis.channels").trim();
		 resourceUpload=PropKit.get("resource.upload.path").trim();
		 resourceDown=PropKit.get("resource.upload.path").trim();
		 weixinToken=PropKit.get("weixin.token").trim();
		 cookie_name=PropKit.get("cookie.name").trim();
		 constants.setBaseDownloadPath(resourceUpload);
		 String fullFile = PathKit.getWebRootPath() + File.separator + "WEB-INF" + "/classes/velocity.properties";
		 InputStream inputStream=null;
		 /**
		  * 加载Velocity配置文件
		  */
		 try {
		     inputStream = new FileInputStream(new File(fullFile));
			 Properties p = new Properties();
			 p.load(inputStream);
			 VelocityRender.setProperties(p);
		 } catch (Exception e) {
			e.printStackTrace();
		 }
	}
	/**
	 * 添加路由
	 */
	@Override
	public void configRoute(Routes routes) {
		AutoBindRoutes autoBindRoutes= new AutoBindRoutes();
		autoBindRoutes.setPackageName("com.information.controller");
		routes.add(autoBindRoutes);
	}
	
	/**
	 * 配置多个数据库连接
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void configPlugin(Plugins plugin) {
	    /**
	     * 配置主数据库
	     */
		DruidPlugin primaryDruid = new DruidPlugin(PropKit.get("primary.jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
	    plugin.add(primaryDruid);
	    AutoTableBindPlugin primaryAtbp = new AutoTableBindPlugin("information",primaryDruid);
	    primaryAtbp.scanPackages("com.information.model.primary"); //扫描com.information.model下的model
	    //如果你只想用注解而不想让没有注解的model被自动注册，则如下使用
	    primaryAtbp.autoScan(false);
	    primaryAtbp.addExcludeClasses(BaseModel.class);
	    primaryAtbp.setShowSql(true);
	    plugin.add(primaryAtbp);
	    /**
	     * 配置从数据库
	     */
  		DruidPlugin slaveDruid= new DruidPlugin(PropKit.get("slave.jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
  	    plugin.add(slaveDruid);
  	    AutoTableBindPlugin slaveAtbp = new AutoTableBindPlugin("information_back",slaveDruid);
  	    slaveAtbp.scanPackages("com.information.model.slave");//扫描com.information.student下的model
  	    slaveAtbp.autoScan(false);
  	    slaveAtbp.addExcludeClasses(SlaveBaseModel.class);
  	    slaveAtbp.setShowSql(true);
  	    plugin.add(slaveAtbp);
  	   
	    plugin.add(new EhCachePlugin());//配置缓存插件
	    //配置redis插件
	    RedisPlugin redis=new RedisPlugin("information",redisHost,6379,redisPassword);
	    redis.getJedisPoolConfig().setMaxTotal(200);
	    redis.getJedisPoolConfig().setMaxIdle(200);
	    plugin.add(redis);
	    plugin.add(new SpringPlugin(SpringBeanManger.getContext()));//集成spring
	    
	}

	/**
	 * 拦截器配置
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new PermissionInterceptor());
		interceptors.add(new ViewContextInterceptor());
		interceptors.add(new IocInterceptor());
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers handlers) {
	   handlers.add(new ContextPathHandler());
	   handlers.add(new RenderingTimeHandler());
	}
	
	/**
	 * 初始化系统数据
	 */
	@Override
	public void afterJFinalStart() {
		try{
		    String menu=JSONObject.fromObject(weiXinService.generateMenu()).toString();
		    int result=weiXinService.createMenu(weiXinService.getAccesstoken().getAccessToken(),menu);
		    if(result==0){
			   LOG.info("菜单创建成功");
		    }
	     }catch(Exception e){
		     e.printStackTrace();
	         LOG.error("菜单创建异常"); 
	     }	
		 new Thread(new Runnable() {
			@Override
			public void run() {
		         RedisListener redisListener = new RedisListener();
		         Redis.use().getJedis().subscribe(redisListener, channels);//订阅频道
				 LOG.info("消息订阅成功");
			}
		}).start();
		 
		new Thread(new Runnable() {
			@Override
			public void run() {
				 JobManger job=(JobManger) SpringBeanManger.getBean("jobManger");
				 job.start();
			}
		}).start();
		LOG.info("数据初始化完毕");
	}
	
	@Override
	public void configEngine(Engine me) {
		
	}
}
