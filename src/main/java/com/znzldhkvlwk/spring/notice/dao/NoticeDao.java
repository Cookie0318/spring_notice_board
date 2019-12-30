package com.znzldhkvlwk.spring.notice.dao;

import java.util.List;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

public interface NoticeDao {
//	public List<Notice> getList();
	public List<Notice> getList(PagingVo vo);
	public int getCountList(); 
	public int write(Notice notice);
	public Notice search(int id);
	public int delete(int id);
	public int modify(Notice notice);
}
