package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestBookService guestbookService;

	// 방명록 첫페이지(등록폼 + 리스트)
	@RequestMapping(value = "/api/guestbook/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList() {

		System.out.println("ApiGuestbookController>addList()");

		return "apiGuestbook/addList";
	}

	// 방명록 리스트 데이터만 보내줘
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestBookVo> list() {

		System.out.println("ApiGuestbookController>List()");

		List<GuestBookVo> gList = guestbookService.getGuestBookList();
		return gList;
	}

	@RequestMapping(value = "/api/guestbook/add", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestBookVo add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("ApiGuestbookController>add()");

		GuestBookVo gVo = guestbookService.addGuest(guestBookVo);

		System.out.println(gVo);
		return gVo;
	}

	// 방명록 삭제
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public String remove(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("apiCotroller > remove()");
		System.out.println(guestBookVo);

		String state = guestbookService.removeGuest(guestBookVo);

		return state;	
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/add2", method = { RequestMethod.GET, RequestMethod.POST })
	// 방명록 저장
	public GuestBookVo add2(@RequestBody GuestBookVo guestbookVo) {
		System.out.println("ApiGuestbookController > add2");
		System.out.println(guestbookVo);
		
		GuestBookVo gVo = guestbookService.addGuest(guestbookVo);
		
		return gVo;
	}

}