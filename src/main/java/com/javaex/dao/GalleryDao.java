package com.javaex.dao;

import java.util.List;

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
	
	public List<GalleryVo> getGalleryList(){
		System.out.println("GalleryDao > getGalleryList()");
		return sqlSession.selectList("Gallery.getGalleryList");
	}

	public GalleryVo getGalleryImage(int no) {
		System.out.println("GalleryDao > getGalleryImage()");
		return sqlSession.selectOne("Gallery.getGalleryImage", no);
	}
}
