package com.znzldhkvlwk.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

public interface NoticeService {
//	public List<Notice> list();
	public List<Notice> list(PagingVo vo);
	public List<Notice> mylist(Map<String, Object> map);
	public int getListCount();
	public int getMyListCount(String id);
	public int write(Notice notice);
	public Notice search(int id);
	public int delete(int id);
	public int modify(Notice notice);
	public String test();
	public int hitPlus(int id);
}
