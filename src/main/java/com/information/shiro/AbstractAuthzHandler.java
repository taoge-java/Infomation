package com.information.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;

public class AbstractAuthzHandler implements AuthzHandler {

	/**
	 * 获得Shiro的Subject对象。
	 * @return
	 */
	 protected Subject getSubject() {
	     return SecurityUtils.getSubject();
	 }

	 public void assertAuthorized() throws AuthorizationException {
		
	 }
}
