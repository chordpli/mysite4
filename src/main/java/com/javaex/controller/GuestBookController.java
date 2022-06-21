package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService gService;
	/***************************************게스트북***************************************/
	// 게스트북 목록
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GuestBookController > list()");
		
		List<GuestBookVo> gList = gService.getGuestBookList();
		
		model.addAttribute("gList", gList);
		
		return "guestbook/addList";
	}
	
	// 게스트북 작성
	@RequestMapping(value = "/post", method = {RequestMethod.GET, RequestMethod.POST})
	public String post(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController > post() ");
		
		guestBookVo.setContent(guestBookVo.getContent().replace("\n", "<br>"));
		gService.postGuestBook(guestBookVo);
			
		return "redirect:./list";
	}
	
	// 게스트북 삭제 폼
	@RequestMapping(value = "/deleteform/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable ("no") int no, Model model) {
		System.out.println("GuestBookController > deleteForm()");
		
		int num = no;
		model.addAttribute("num", num);
		
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam ("no") int num
						,@RequestParam ("password") String password) {
		System.out.println("GuestBookController > delete()");
		
		String pw = gService.getPassword(num);
		
		if(pw.equals(password)) {
			gService.deletePost(num);
		}else {
			System.out.println("비밀번호가 틀립니다.");
		}
		
		return "redirect:/guestbook/list";
	}
	
	

}
