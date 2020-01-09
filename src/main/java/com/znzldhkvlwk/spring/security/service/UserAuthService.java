package com.znzldhkvlwk.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.znzldhkvlwk.spring.member.Member;
import com.znzldhkvlwk.spring.member.Role;
import com.znzldhkvlwk.spring.security.dao.ImplUserDao;
import com.znzldhkvlwk.spring.security.dao.UserDao;

public class UserAuthService implements UserDetailsService{


	private ImplUserDao userDao;
	
	public UserAuthService() {
		
	}
	
	public UserAuthService(ImplUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = userDao.search(username);
		System.out.println(member.getId());
		System.out.println(member.getPassword());
		String role_ = userDao.getRole(username);
		Role role = new Role();
		role.setName(role_);
		
		List<Role> roles = new ArrayList<Role>();
		member.setAuthorities(roles);
		
		return member;
	}
	
}
