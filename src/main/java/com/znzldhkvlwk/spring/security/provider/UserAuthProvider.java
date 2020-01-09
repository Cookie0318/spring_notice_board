package com.znzldhkvlwk.spring.security.provider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.znzldhkvlwk.spring.member.Member;

public class UserAuthProvider extends DaoAuthenticationProvider{
	private UserDetailsService userDetailsService;
	private PasswordEncoder encoder;
	
	public UserAuthProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
		this.userDetailsService = userDetailsService;
		this.encoder = encoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
		String userId = token.getName();
		
		Member member = null;
		
		if(!StringUtils.isEmpty(userId)) {
			member = (Member)userDetailsService.loadUserByUsername(userId);
		}
		
		if(ObjectUtils.isEmpty(member)) {
			throw new UsernameNotFoundException("invalid username");
			
		}
		
		String password = member.getPassword();
		
		if(password.equals(encoder.encode(String.valueOf(token.getCredentials())))) {
			throw new BadCredentialsException("invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(member, password, member.getAuthorities());
		
		
	}
	
}
