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

	public List<GuestBookVo> getGuestBookList() {
		return guestBookDao.getGuestBookList();
	}

	public int postGuestBook(GuestBookVo guestBookVo) {
		return guestBookDao.postGuestBook(guestBookVo);
	}


	// 방명록 저장(ajax)
	public GuestBookVo addGuest(GuestBookVo guestbookVo) {
		System.out.println("GuestBookService>addGuest()");

		// 저장
		System.out.println("전-->" + guestbookVo);
		int count = guestBookDao.insertSelectKey(guestbookVo);
		System.out.println("후-->" + guestbookVo);

		int no = guestbookVo.getNo();

		// 방금저장한 1개의 데이터를 가져온다
		GuestBookVo gVo = guestBookDao.getGuest(no);

		return gVo;
	}

	// 방명록 삭제(ajax)
	public String removeGuest(GuestBookVo guestBookVo) {
		System.out.println(guestBookVo);
		String state;

		int count = guestBookDao.guestDelete(guestBookVo);

		if (count > 0) {
			state = "success";
		} else {
			state = "fail";
		}
		return state;
	}

	public int deletePost(int no) {
		return guestBookDao.deletePost(no);
	}

	public String getPassword(int no) {
		return guestBookDao.getPassword(no);
	}

}
