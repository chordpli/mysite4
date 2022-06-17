package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list() {
		System.out.println("GuestBookController > list()");
		
		return "guestbook/addList";
	}
	
	@RequestMapping(value = "/deleteform", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestBookController > deleteForm()");
		
		return "guestbook/deleteForm";
	}
	

}
