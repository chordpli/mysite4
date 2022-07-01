package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao gDao;
	
	public String save(MultipartFile file, int userNo, String content ) {
		System.out.println("GalleryService > save()");
		String saveDir = "C:\\javaStudy\\upload";
		
		// 오리지날 파일명, 저장경로 + 파일(랜덤)이름, 파일 사이즈
		String orgName = file.getOriginalFilename();
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		// 파일 저장명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString();
		
		// 파일 경로
		String filePath = saveDir + "\\" +saveName;
		
		// 파일 사이즈
		long size = file.getSize();
		
		// Vo로 묶기
		GalleryVo gVo = new GalleryVo(userNo, content, filePath, orgName, saveName, size);
		System.out.println(gVo);
		
		int count = gDao.insertImage(gVo);
		System.out.println("GalleryService > inserImage()" + gVo);
		System.out.println(count + "저장되었습니다.");
		
		// (2)파일 저장
		
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos =  new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}
		
		return saveName;
		
	}
	
	

}
