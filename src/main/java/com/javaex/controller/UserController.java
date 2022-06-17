package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/***************************************로그인***************************************/
	// 로그인 폼
	@RequestMapping(value = "/loginform", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController > loginForm()");
		
		return "user/loginForm";
	}
	
	/***************************************회원가입***************************************/
	// 로그인 폼
	@RequestMapping(value = "/joinform", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController > joinForm()");
		
		return "user/joinForm";
	}
	
	
	@RequestMapping(value = "/joinok", method = {RequestMethod.GET, RequestMethod.POST} )
	public String joinOk() {
		System.out.println("UserController > loginOk()");
		
		return "user/joinOk";
	}
	
	
	
	/***************************************수정***************************************/
	// 수정 폼
	@RequestMapping(value = "/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		System.out.println("UserController > modifyForm()");
		
		return "user/modifyForm";
	}

}
