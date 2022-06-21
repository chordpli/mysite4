package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getBoardList(){
		return boardDao.getBoardList();
	}
	
	public List<BoardVo> getBoardList(String keyword){
		return boardDao.getBoardList(keyword);
	}
	
	public BoardVo getBoardContent(int no) {
		return boardDao.getBoardContent(no);
	}
	
	public int modifyPost(BoardVo boardVo) {
		return boardDao.modifyPost(boardVo);
	}
	
	public int deletePost(int no) {
		return boardDao.deletePost(no);
	}
	
	public int posting(BoardVo boardVo) {
		return boardDao.posting(boardVo);
	}
	
	public int postHit(int no) {
		return boardDao.postHit(no);
	}
	

}