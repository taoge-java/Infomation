package com.information.shiro;

import org.apache.shiro.authz.AuthorizationException;

public interface AuthzHandler {

	/**
	 * 访问控制检查
	 * @throws AuthorizationException 授权异常
	 */
	public void assertAuthorized()throws AuthorizationException;
}
