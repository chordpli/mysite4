package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	
//	/***************************************게시판 목록***************************************/
	
	// 게시판 목록 (+ 검색 추가)
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(required = false, value= "keyword") String keyword, HttpServletRequest req, Model model) {
		System.out.println("BoardController > list");
		HttpSession session = req.getSession();
		UserVo authUser =  (UserVo) session.getAttribute("authUser");
		
		session.setAttribute("authUser", authUser);
		
		if(keyword != null) {
			List<BoardVo> bList = bService.getBoardList(keyword);
			model.addAttribute("bList", bList);
		}else {
			List<BoardVo> bList = bService.getBoardList();
			model.addAttribute("bList", bList);
		}
		
		return "board/list";
	}
	
//	// 게시판 목록
//	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
//	public String list(HttpServletRequest req, Model model) {
//		System.out.println("BoardController > list");
//		HttpSession session = req.getSession();
//		UserVo authUser =  (UserVo) session.getAttribute("authUser");
//		
//		session.setAttribute("authUser", authUser);
//		
//		List<BoardVo> bList = bService.getBoardList();
//		model.addAttribute("bList", bList);
//		
//		return "board/list";
//	}
	

//	// 게시판 검색 목록
//	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
//	public String list(@RequestParam("keyword") String keyword, HttpServletRequest req, Model model) {
//		System.out.println("BoardController > list");
//		HttpSession session = req.getSession();
//		UserVo authUser =  (UserVo) session.getAttribute("authUser");
//		
//		session.setAttribute("authUser", authUser);
//		
//		List<BoardVo> bList = bService.getBoardList(keyword);
//		model.addAttribute("bList", bList);
//		
//		return "board/list";
//	}
	
	/***************************************게시글***************************************/
	// 게시글 작성 폼
	@RequestMapping(value = "/writeform", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(HttpServletRequest req) {
		System.out.println("BoardController > writeForm");
		
		HttpSession session = req.getSession();
		UserVo authUser =  (UserVo) session.getAttribute("authUser");
		
		session.setAttribute("authUser", authUser);
		
		return "board/writeForm";
	}
	
	// 게시물 업로드
		@RequestMapping(value = "/posting", method= {RequestMethod.GET, RequestMethod.POST} )
		public String posting(@ModelAttribute BoardVo boardVo) {
			
			boardVo.setContent(boardVo.getContent().replace("\n", "<br>"));
			bService.posting(boardVo);
			
			return "redirect:./list";
		}
		
		
	// 게시글 보기
	@RequestMapping(value = "/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable ("no") int no, Model model, HttpServletRequest req) {
		System.out.println("BoardController > read");
		HttpSession session = req.getSession();
		UserVo authUser =  (UserVo) session.getAttribute("authUser");
		
		BoardVo bContent = bService.getBoardContent(no);
		if(authUser != null) {
			if(authUser.getNo() != bContent.getUserNo()) {
				bService.postHit(no);
			}
		}else {
			bService.postHit(no);
		}
		
		bContent = bService.getBoardContent(no);
		model.addAttribute("bContent", bContent);
		
		
		return "board/read";
	}
	
	// 게시글 수정 폼
	@RequestMapping(value = "/modifyform/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@PathVariable ("no") int no, Model model) {
		System.out.println("BoardController > modifyform");
		
		BoardVo bContent = bService.getBoardContent(no);
		
		model.addAttribute("bContent", bContent);
		
		
		return "board/modifyForm";
	}
	
	// 게시글 수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > modify()");
		
		// 줄바꿈
		boardVo.setContent(boardVo.getContent().replace("\n", "<br>"));
		
		bService.modifyPost(boardVo);
		
		return "redirect:./read/" + boardVo.getNo();
	}
	
	// 게시글 삭제
	@RequestMapping(value = "/delete/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable ("no") int no) {
		
		bService.deletePost(no);
		
		return "redirect:../list";
	}
	
		
	
	

}
