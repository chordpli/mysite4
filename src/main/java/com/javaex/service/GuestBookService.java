package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getGuestBookList(){
		return guestBookDao.getGuestBookList();
	}
	
	public int postGuestBook(GuestBookVo guestBookVo) {
		return guestBookDao.postGuestBook(guestBookVo);
	}
	
	public GuestBookVo insertGuest(GuestBookVo guestBookVo) {
		System.out.println(guestBookVo);
		int count = guestBookDao.insertGuest(guestBookVo); 
		System.out.println(guestBookVo);
		
		int no = guestBookVo.getNo();
		GuestBookVo gVo = guestBookDao.getGuest(no);
		return gVo;
	}
	
	public int deletePost(int no) {
		return guestBookDao.deletePost(no);
	}
	
	public String getPassword(int no) {
		return guestBookDao.getPassword(no);
	}

}
