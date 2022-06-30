package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;

	public List<GuestBookVo> getGuestBookList() {
		return sqlSession.selectList("GuestBook.getGuestBookList");
	}

	public int postGuestBook(GuestBookVo guestBookVo) {
		return sqlSession.insert("GuestBook.postGuestBook", guestBookVo);

	}

	

	public int deletePost(int no) {
		return sqlSession.delete("GuestBook.deletePost", no);
	}

	public String getPassword(int no) {
		return sqlSession.selectOne("GuestBook.getPassword", no);
	}

	
	public int insertSelectKey(GuestBookVo guestBookVo) {
		System.out.println(guestBookVo);
		int count = sqlSession.insert("GuestBook.insertSelectKey", guestBookVo);

		return count;
	}
	
	public GuestBookVo getGuest(int no) {
		System.out.println("getGuest");
		return sqlSession.selectOne("GuestBook.getGuest", no);
	}

	public int guestDelete(GuestBookVo guestBookVo) {
		System.out.println("Dao > guestDelete");

		System.out.println(guestBookVo);

		return sqlSession.delete("GuestBook.gdelete", guestBookVo);
	}

}
