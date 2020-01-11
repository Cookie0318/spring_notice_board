package com.znzldhkvlwk.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.znzldhkvlwk.spring.member.Member;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		Member member = (Member)userService.loadUserByUsername(username);
		
		String encoded_password = passwordEncoder.encode(password);
		System.out.println(encoded_password);		
		
		if(!matchPassword(password, member.getPassword())) {
			throw new BadCredentialsException(username);
		}
		
		if(!member.isEnabled()) {
			throw new BadCredentialsException(username);
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, member.getAuthorities());
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	
	private boolean matchPassword(String loginPwd, String password) {
		return passwordEncoder.matches(loginPwd, password);
	}

}
