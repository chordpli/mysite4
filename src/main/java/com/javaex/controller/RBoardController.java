package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;

@Controller
@RequestMapping("/rboard")
public class RBoardController {
	
	@Autowired
	private RBoardService rService;
	
	@RequestMapping(value = "/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model){
		System.out.println("RBoardController > list()");
		
		List<RBoardVo> rList = rService.getList();
		
		model.addAttribute("rList", rList);
		
		return "rBoard/list";
	}
	
	@GetMapping(value = "/read/{no}")
	public String read(@PathVariable ("no") int no, Model model) {
		System.out.println("RBoardController > read()");
		
		RBoardVo bContent = rService.readPost(no);
		
		model.addAttribute("bContent", bContent);
		return "rBoard/read";
	}
	
	
	@GetMapping(value = "/writeform")
	public String writeForm() {
		System.out.println("BoardController > writeForm()");
		
		return "rBoard/writeForm";
	}
	
	@GetMapping(value = "/posting")
	public String posting(@ModelAttribute RBoardVo rVo) {
		System.out.println("BoardController > post()");
		
		System.out.println(rVo);
		
		rVo.setContent(rVo.getContent().replace("\n", "<br"));
		rService.postBoard(rVo);
		
		return "redirect:./list";
	}
	
	@GetMapping(value = "/replywriteform/{no}")
	public String replyWriteForm(@PathVariable ("no") int no, Model model) {
		
		RBoardVo rVo = rService.getStair(no);
		
		model.addAttribute("rVo", rVo);
		
		return "rBoard/replyWriteForm";
	}
	
	@GetMapping(value = "/replyposting")
	public String replyPosting(@ModelAttribute RBoardVo rVo) {
		System.out.println("BoardController > replyPost()");
		
		System.out.println(rVo);
		
		
		rService.postReply(rVo);
		
		return "redirect:./list";
	}
	
	
	

}
