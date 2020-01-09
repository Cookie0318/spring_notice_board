package com.znzldhkvlwk.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.znzldhkvlwk.spring.member.Member;

public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserAuthDAO userAuthDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = userAuthDAO.getUserById(username);
		if(member == null) {
			throw new UsernameNotFoundException(username);
		}
		return member;
	}

}
