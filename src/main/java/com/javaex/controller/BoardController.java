package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	
	/***************************************게시판 목록***************************************/
	// 게시판 목록
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardController > list");
		
		List<BoardVo> bList = bService.getBoardList();
		model.addAttribute("bList", bList);
		
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
	@RequestMapping(value = "/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable ("no") int no, Model model) {
		System.out.println("BoardController > read");
		
		BoardVo bContent = bService.getBoardContent(no);
		
		model.addAttribute("bContent", bContent);
		
		
		return "board/read";
	}
	
	// 게시글 수정
	@RequestMapping(value = "/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		System.out.println("BoardController > list");
		
		return "board/modifyForm";
	}
	

}
