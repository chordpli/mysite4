package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	// 리스트
	public List<RBoardVo> rBoardList(int startRNum, int endRNum){
		System.out.println("RBoardDao > rBoardList()");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("startRNum", startRNum);
		map.put("endRNum", endRNum);
		
		return sqlSession.selectList("rBoard.rBoardList", map);
	}
	
	// 전체 글 갯수
	public int selectTotalCnt() {
		System.out.println("RBoardDao > selectTotalCnt()");
		return sqlSession.selectOne("rBoard.selectTotalCnt");
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
	
	// 답글시 orderNo 수정
	public int orderUpdate(RBoardVo rVo) {
		System.out.println("RBoardDao > orderUpdate()");
		return sqlSession.update("rBoard.orderUpdate", rVo);
	}
	
	// 게시글 삭제
	public int deleteBoard(int no) {
		System.out.println("RBoardDao > deleteBoard");
		return sqlSession.delete("rBoard.deleteBoard", no);
	}
	
	
}
