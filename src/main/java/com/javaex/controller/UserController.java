package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
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
	
	// 회원가입
	
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > join()");
		
		userService.join();
		
		return "";
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
