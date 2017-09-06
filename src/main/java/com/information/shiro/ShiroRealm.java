package com.information.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import com.information.model.primary.system.SystemAdmin;
import com.information.utils.Md5Utils;

/**
 * shiro安全认证
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月5日下午4:44:30
 */
public class ShiroRealm extends AuthenticatingRealm{

	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) 
			throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		String userName = usernamePasswordToken.getUsername();
		SystemAdmin admin = SystemAdmin.dao.findFirst("select * from system_admin where login_name=?",userName);
		String password = Md5Utils.getMd5(new String(usernamePasswordToken.getPassword()), admin.getStr("encrypt"));
		usernamePasswordToken.setPassword(password.toCharArray());
		if(admin == null){
			throw new UnknownAccountException("用户不存在");
		}
		//以下数据属于数据库中的用户名密码
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, 
				admin.getStr("password"), getName());
		return info;
	}
}
