package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getBoardList(){
		System.out.println("BoardDao > getBoardList()");
		return sqlSession.selectList("Board.getBoardList");
	}
	
	public List<BoardVo> getBoardList(String keyword){
		System.out.println("BoardDao > getBoardList(String Keyword)");
		return sqlSession.selectList("Board.searchPost", keyword);
	}
	
	public BoardVo getBoardContent(int no) {
		System.out.println("BoardDao > getBoardContent()");
		return sqlSession.selectOne("Board.getBoardContent", no);
	}
	
	public int modifyPost(BoardVo boardVo) {
		System.out.println("BoardDao > modifyContent()");
		return sqlSession.update("Board.modifyPost", boardVo);
	}
	
	public int deletePost(int no) {
		System.out.println("BoardDao > deletePost()");
		return sqlSession.delete("Board.deletePost", no);
	}
	
	public int posting(BoardVo boardVo) {
		System.out.println("BoardDao > posting()");
		return sqlSession.insert("Board.posting", boardVo);
	}
	
	public int postHit(int no) {
		System.out.println("BoardDao > postHit");
		return sqlSession.update("Board.postHit", no);
	}
	


}
