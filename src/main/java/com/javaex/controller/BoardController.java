package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	/***************************************게시판 목록***************************************/
	// 게시판 목록
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list() {
		System.out.println("BoardController > list");
		
		return "board/list";
	}
	
	/***************************************게시글***************************************/
	// 게시글 작성
	@RequestMapping(value = "/writeform", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController > writeForm");
		
		return "board/writeForm";
	}
	
	// 게시글 보기
	@RequestMapping(value = "/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read() {
		System.out.println("BoardController > read");
		
		return "board/read";
	}
	
	// 게시글 수정
	@RequestMapping(value = "/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		System.out.println("BoardController > list");
		
		return "board/modifyForm";
	}
	

}
