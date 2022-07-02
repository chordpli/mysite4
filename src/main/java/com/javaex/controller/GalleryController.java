package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService gService;
	
	
	@RequestMapping(value = "/gallery/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String gallery(Model model) {
		System.out.println("GalleryController > gallery()");
		
		List<GalleryVo> gList = gService.getGalleryList();
		
		model.addAttribute("gList", gList);
		
		return "gallery/list";
	}
	
	@RequestMapping(value = "/gallery/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file
						,@RequestParam("userNo") int userNo
						,@RequestParam("content") String content) {
		System.out.println("GalleryController > upload()");
		System.out.println(file + " / " + userNo + " / " + content);
		
		
		System.out.println(file.getOriginalFilename());
		
		
		String saveName = gService.save(file, userNo, content);
		
		
		return "redirect:/gallery/list";
	}
	
	@RequestMapping(value = "/gallery/image", method = {RequestMethod.GET, RequestMethod.POST})
	public String image(Model model) {
		
		GalleryVo gVo = gService.getGalleryImage();
		model.addAttribute("gVo", gVo);
		
		return gVo;
	}
}
