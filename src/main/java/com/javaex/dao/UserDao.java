package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	private SqlSession sqlSession;
	
	public int join() {
		return sqlSession.insert("User.join"); 
	}

}
