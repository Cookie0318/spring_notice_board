package com.znzldhkvlwk.spring.notice.dao;

import java.util.List;
import java.util.Map;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

public interface NoticeDao {
//	public List<Notice> getList();
	public List<Notice> getList(PagingVo vo);
	public List<Notice> getMyList(Map<String, Object> map);
	public int getCountList();
	public int getCountMyList(String id);
	public int write(Notice notice);
	public Notice search(int id);
	public int delete(int id);
	public int modify(Notice notice);
	public String test();
	public int hitPlus(int id);
}
