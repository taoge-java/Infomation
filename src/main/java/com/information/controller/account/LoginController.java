package com.information.controller.account;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.information.constant.CommonConstant;
import com.information.constant.CommonEnum.LogType;
import com.information.controller.base.BaseController;
import com.information.dao.OnlineManger;
import com.information.dao.OnlineUser;
import com.information.dao.UserSession;
import com.information.model.system.SystemAdmin;
import com.information.service.system.SystemRoleService;
import com.information.utils.DateUtil;
import com.information.utils.ImageUtil;
import com.information.utils.IpUtils;
import com.information.utils.Md5Utils;
import com.information.utils.ResultCode;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Log;

/**
 * 用户登录
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 上午9:47:48
 */
@ControllerBind(controllerKey="/account")
public class LoginController extends BaseController{
	
	private static final Log LOG=Log.getLog(LoginController.class);
	
	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	private OnlineManger onlineManger;
	/**
	 * 用户登录页面
	 */
	public void index(){
		String userName=getCookie(CommonConstant.COOKIE_USERNAME);
		String password=getCookie(CommonConstant.COOKIE_PASSWORD);
		if(userName!=null&&password!=null){
			setAttr("userName", userName);
			setAttr("password", password);
		}
		rendView("/account/login.vm");
	}

	/**
	 * 验证码
	 */
	public void image(){
		ImageUtil image=new ImageUtil();
		render(image);
	}

	public void login(){
		String userName=getPara("username");
		String password=getPara("password");
		String code=getPara("code");
		String number=(String) this.getSession().getAttribute(CommonConstant.IMAGE_CODE);//获取session中得验证码
		String remberPassword[]=getParaValues("checkbox");//判断用户是否记住密码
		if(remberPassword!=null&&remberPassword.length>0){//将用户名密码保存在cookie中
			setCookie(CommonConstant.COOKIE_USERNAME,userName,60*60*24*30);
			setCookie(CommonConstant.COOKIE_PASSWORD,password,60*60*24*30);
		}else{//清除cookie
			removeCookie(CommonConstant.COOKIE_USERNAME, "/");
			removeCookie(CommonConstant.COOKIE_PASSWORD, "/");
		}
		
		//ehcache 缓存
//		SystemAdmin admin1=CacheKit.get(Constant.ONE_MINUTE, "user");
//		SystemAdmin admin=SystemAdmin.dao.findFirstByCache(Constant.ONE_MINUTE,"user"," select * from system_admin where login_name=?",userName);
		
		//System.err.println(admin1);
		SystemAdmin admin=SystemAdmin.dao.findFirst("select * from system_admin where login_name=?",userName);
		if(admin==null){
			renderJson(new ResultCode(ResultCode.FAIL,"用户不存在"));
			return;
		}
		if(StringUtils.isBlank(code)){//如果用户没有输入验证码
			   loginSrvice(admin,password);
		}else{//出现验证码
			if(code.equalsIgnoreCase(number)){
		     	loginSrvice(admin,password);//验证码不区分大小写
			}else{
				//admin.set("login_error", admin.getInt("login_error")+1);
				//admin.update();
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("code", ResultCode.FAIL);
				map.put("message", "验证码错误");
				//map.put("errorCount", admin.getInt("login_error"));
				LOG.error("验证码错误");
				renderJson(map);
				return;
			}
		}
	}
	/**
	 * 
	 * @param admin
	 * @param password
	 * @param code
	 * @param number
	 */
	private void loginSrvice(SystemAdmin admin, String password) {
		UserSession session=onlineManger.getUserSessionById(admin.getInt("id"));
		if(session!=null){//如果用户已经登录
		    onlineManger.remove(session);
		}
		if(admin.getStr("password").equals(Md5Utils.getMd5(password, admin.getStr("encrypt")))){
			if(admin.getBoolean("disabled_flag")){
				renderJson(new ResultCode(ResultCode.FAIL, "用户已被禁用,请联系管理员"));
				return;
			}
			loginSuccess(admin);//登录成功
			renderJson(new ResultCode(ResultCode.SUCCESS, "登录成功"));
		}else{
			LOG.error("用户名或密码错误");
			renderJson(new ResultCode(ResultCode.FAIL, "用户名或密码错误"));
			return;
		}
	}

	/**
	 * 登录成功
	 */
	private void loginSuccess(SystemAdmin admin) {
		admin.set("last_login_time",DateUtil.getDate());
		admin.set("last_login_ip",IpUtils.getAddressIp(getRequest()));
		admin.set("login_count",admin.getInt("login_count"+1));
		admin.update();
		OnlineUser online=new OnlineUser();
		UserSession session=new UserSession();
		session.setSessionId(online.getSessionId());
		session.setUserId(admin.getInt("id"));
		session.setHeartTime(System.currentTimeMillis());
		session.setLast_login_time(DateUtil.getStrDate(admin.getDate("last_login_time")));
		session.setLoginName(admin.getStr("login_name"));
		session.setSuperFlag(admin.getBoolean("super_flag") ? true:false);
		session.setNickName(admin.getStr("nickname"));
		session.setMobile(admin.getStr("mobile"));
		setSessionAttr(CommonConstant.SESSION_ID_KEY, session);
		onlineManger.add(session);
		//非超级管理员加载权限
		if(!session.isSuperFlag()){
			loadPermissions(admin);
		}
		systemLog(getCurrentUser().getLoginName()+"登录了系统",LogType.LOGIN.getValue());
	}
	/**
	 * 用户注销
	 */
	public void exit(){		
		if(getCurrentUser()!=null){
			if(onlineManger.getUserSession(getCurrentUser().getSessionId()) != null){//移除sessionid
				onlineManger.remove(getCurrentUser());
        	}
			systemLog(getCurrentUser().getLoginName()+"登出系统",LogType.LOGIN.getValue());
			getRequest().getSession().removeAttribute(CommonConstant.SESSION_ID_KEY);
			getRequest().getSession().invalidate();//用户注销
			redirect("/",false);
		}
	}
	
	/**
	 *获取用户登录错误次数，大于3次出现验证码
	 */
//	public void getErrorCount(){
//		String userName=getPara("userName");
//		int error_count=0;
//		SystemAdmin systemAdmin=SystemAdmin.dao.findFirst("select login_error from system_admin where login_name=?",userName);
//		if(systemAdmin==null){
//			renderJson(new ResultCode(ResultCode.SUCCESS, "0"));
//		}else{
//			error_count=systemAdmin.getInt("login_error");
//			renderJson(new ResultCode(ResultCode.SUCCESS, error_count+""));
//		}
//	}
	/**
	 * 加载权限
	 */
	private void loadPermissions(SystemAdmin admin){
		Set<String> operCode=systemRoleService.findRoleById(admin.getInt("role_id"));//操作列表
		Set<String> menuCode=addMenuCode(operCode);//菜单列表
		getCurrentUser().setOperCodeSet(operCode);
		getCurrentUser().setMenuCodeSet(menuCode);
	}

	/**
	 * 获取菜单列表
	 * @param operCode
	 */
	private Set<String> addMenuCode(Set<String> operCode) {
		Set<String> menuCode=new LinkedHashSet<String>();
		for(String code:operCode){
			String[] codes=code.split("_");
			String codeLevel="";
			for(int i=0;i<codes.length-2;i++){
				if("".equals(codeLevel)){
					codeLevel+=codes[i]+"_"+codes[i+1];
				}else{
					codeLevel+="_"+codes[i+1];
				}
				menuCode.add(codeLevel);
			}
		}
		return menuCode;
	}
}
