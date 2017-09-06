package com.information.shiro;

import java.util.List;

import org.apache.shiro.authz.AuthorizationException;

public class CompositeAuthzHandler implements AuthzHandler {

	private final List<AuthzHandler> authzHandlers;

	public CompositeAuthzHandler(List<AuthzHandler> authzHandlers){
		this.authzHandlers = authzHandlers;
	}

	public void assertAuthorized() throws AuthorizationException {
		for(AuthzHandler authzHandler : authzHandlers){
			authzHandler.assertAuthorized();
		}
	}
}
