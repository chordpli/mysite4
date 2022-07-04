package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	// 리스트
	public List<RBoardVo> rBoardList(){
		System.out.println("RBoardDao > rBoardList()");
		return sqlSession.selectList("rBoard.rBoardList");
	}
	
	// 글 읽기
	public RBoardVo rBoardRead(int no) {
		System.out.println("RBoardDao > rBoardRead()");
		return sqlSession.selectOne("rBoard.rBoardRead", no);
	}
	
	// 계층 불러오기
	public RBoardVo getStair(int no) {
		System.out.println("RBoardDao > getStair()");
		return sqlSession.selectOne("rBoard.getStair", no);
	}
	
	// 게시글 포스팅
	public int insertBoard(RBoardVo rVo) {
		System.out.println("RBoardDao > insertBoard()");
		return sqlSession.insert("rBoard.insertBoard", rVo);
	}
	
	
	// 답글 포스팅
	public int replyBoard(RBoardVo rVo) {
		System.out.println("RBoardDao > replyBoard()");
		return sqlSession.insert("rBoard.replyBoard", rVo);
	}
	
	

	
}
