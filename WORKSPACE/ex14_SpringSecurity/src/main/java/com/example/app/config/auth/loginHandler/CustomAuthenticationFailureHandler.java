package com.example.app.config.auth.loginHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
	
		System.out.println("로그인 실패  : " + exception);
		
		request.setAttribute("msg", "로그인 실패.. ID/PW 확인하세요");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

	}

}
