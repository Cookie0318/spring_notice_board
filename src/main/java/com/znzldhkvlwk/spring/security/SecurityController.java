package com.znzldhkvlwk.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class SecurityController {
	
	@RequestMapping("/login_form")
	public String login_form() {
		return "login_form";
	}
}
