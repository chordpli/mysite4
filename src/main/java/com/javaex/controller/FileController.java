package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;

	@RequestMapping(value = "/fileupload/form", method = {RequestMethod.GET, RequestMethod.POST})
	public String Form() {
		System.out.println("FileController > form()");
		
		return "fileupload/form";
	}
	
	@RequestMapping(value ="/fileupload/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileController > upload");
		System.out.println(file.getOriginalFilename());
		
		String saveName = fileService.save(file);
		
		model.addAttribute("saveName", saveName);
		
		return "fileupload/result";
		
	}
}
