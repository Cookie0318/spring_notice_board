package com.znzldhkvlwk.spring.notice.service;

import java.util.List;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

public interface NoticeService {
//	public List<Notice> list();
	public List<Notice> list(PagingVo vo);
	public int getListCount();
	public int write(Notice notice);
	public Notice search(int id);
	public int delete(int id);
	public int modify(Notice notice);
	public String test();
}
