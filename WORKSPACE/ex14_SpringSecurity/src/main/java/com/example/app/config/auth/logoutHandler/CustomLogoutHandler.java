package com.example.app.config.auth.logoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class CustomLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("CustomLogoutHandler's logout() invoke...");
		//세션초기화
		HttpSession session = (HttpSession)request.getSession(false);
		if(session!=null)
			session.invalidate();
		
	}

}
