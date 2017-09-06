package com.information.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;

public class UserAuthzHandler extends AbstractAuthzHandler {

	private static UserAuthzHandler uah = new UserAuthzHandler();

	private UserAuthzHandler(){}

	public static  UserAuthzHandler me(){
		return uah;
	}

	public void assertAuthorized() throws AuthorizationException {
		if (getSubject().getPrincipal() == null) {
            throw new UnauthenticatedException("Attempting to perform a user-only operation.  The current Subject is " +
                    "not a user (they haven't been authenticated or remembered from a previous login).  " +
                    "Access denied.");
        }
	}
}
