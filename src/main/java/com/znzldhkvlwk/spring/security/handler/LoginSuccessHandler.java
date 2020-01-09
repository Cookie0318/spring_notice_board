package com.znzldhkvlwk.spring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.znzldhkvlwk.spring.member.service.MemberService;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private static int TIME = 60 * 60; //1시간
	
//	@Autowired
//	@Qualifier("MemberService")
//	private MemberService service;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.getSession().setMaxInactiveInterval(TIME);
		/*
		 * exmaple
		 * 로그인 시간 기록 하기
		 */
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	

}
