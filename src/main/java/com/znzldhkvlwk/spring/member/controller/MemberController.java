package com.znzldhkvlwk.spring.member.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.znzldhkvlwk.spring.member.Member;
import com.znzldhkvlwk.spring.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	@Qualifier("MemberService")
	private MemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping("/join_form")
	public String join(Member member) {
		return "member/join_form";
	}
	
	@RequestMapping(value="/joinOk", method=RequestMethod.POST)
	public String joinOk(Member member) {
		//비밀번호 암호화
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		//나이계산(yyyy-MM-dd)
		String birthYear = (member.getBirthday()).substring(0, 4);
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String thisYear = sf.format(today);
		int age = Integer.parseInt(thisYear) - Integer.parseInt(birthYear) + 1;
		//Member 객체에 대입
		member.setAge(age);
		
		int result = service.join(member);
		if(result == 0) System.out.println("회원가입 오류");
		else System.out.println("회원가입 완료");
		return "member/joinOk";
	}
	
	
	@RequestMapping("/modify_form")
	public String modify(Member member, Principal principal, Model model) {
		Member mem = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute(mem);
		return "member/modify_form";
	}
	
	@RequestMapping(value="/modifyOk" , method=RequestMethod.POST)
	public String modifyOk(Member member) {
		service.modify(member);
		
		return "member/modifyOk";
	}
		
}
		
