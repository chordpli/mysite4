package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public int insertImage(GalleryVo galleryVo) {
		System.out.println("GalleryDao > insertImage");
		return sqlSession.insert("Gallery.insertImage", galleryVo);
		
	}

}
