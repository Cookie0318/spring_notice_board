package com.znzldhkvlwk.spring.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class SecurityController {
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping("/login_form")
	public String login_form() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//이미 로그인 되어있으면 home.jsp 리턴
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return "home";
		}
		return "sec/login_form";
	}
}
