package com.znzldhkvlwk.spring.security.dao;

import com.znzldhkvlwk.spring.member.Member;

public interface UserDao {
	public Member search(String id);
	public String getRole(String id);
}
