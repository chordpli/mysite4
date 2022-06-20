package com.javaex.controller;

import javax.servlet.http.HttpSession;

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
			
		
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println("login");
			return "redirect:../main";
		}else {
			System.out.println("로그인 실패입니다.");
			return "redirect:./loginform?result=fail";
		}
		
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/mysite4/main";
	}
	
	
	/***************************************회원가입***************************************/
	
	
	// 회원가입 폼
	@RequestMapping(value = "/joinform", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController > joinForm()");
		
		return "user/joinForm";
	}
	
	// 회원가입
	
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > join()");
		
		int count = userService.join(userVo);
		
		System.out.println(count);
		
		return "redirect:/user/joinok";
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
