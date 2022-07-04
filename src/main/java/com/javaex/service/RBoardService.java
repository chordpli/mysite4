package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {
	
	@Autowired
	private RBoardDao rDao;
	
	
	// 리스트
	public List<RBoardVo> getList(){
		System.out.println("RBoardService > getList()");
		return rDao.rBoardList();
	}
	
	// 글 읽기
	public RBoardVo readPost(int no) {
		System.out.println("RBoardService > readPost()");
		return rDao.rBoardRead(no);
	}
	
	// 계층 불러오기
	public RBoardVo getStair(int no) {
		System.out.println("RBoardService > getStair");
		return rDao.getStair(no);
	}
	
	// 게시글 포스팅
	public int postBoard(RBoardVo rVo) {
		System.out.println("RBoardService > postBoard()");
		return rDao.insertBoard(rVo);
	}
	
	// 답글 포스팅
	public int postReply(RBoardVo rVo) {
		System.out.println("RBoardService > postBoard()");
		String space = "&nbsp;&nbsp;&nbsp;&nbsp;";
		
		int count;
		
		rVo.setOrderNo(rVo.getOrderNo()+1);
		rVo.setDepth(rVo.getDepth()+1);
		rVo.setContent(rVo.getContent().replace("\n", "<br"));
		
		for(int i = 0; i < rVo.getDepth(); i++ ) {
			space += space;
		}
		
		rVo.setTitle(space + "ㄴ" +rVo.getTitle());
		
		count = rDao.replyBoard(rVo);
		return count;
	}
	
	public int replyBoard() {
		System.out.println("RboardService > replyBoard");
		return 0;
	}

}
