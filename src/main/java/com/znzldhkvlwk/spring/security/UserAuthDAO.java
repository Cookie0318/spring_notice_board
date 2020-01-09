package com.znzldhkvlwk.spring.security;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.znzldhkvlwk.spring.member.Member;

public class UserAuthDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "com.znzldhkvlwk.spring.security.UserMapper";
	
	public Member getUserById(String username) {
		return sqlSession.selectOne(namespace+".selectUserById", username);
	}
}
