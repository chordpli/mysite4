package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int join(UserVo userVo) {
		return sqlSession.insert("User.join", userVo); 
	}
	
	public UserVo login(UserVo userVo) {
		
		UserVo authUser = sqlSession.selectOne("User.login",userVo);
		// return sqlSession.selectOne("User.login", userVo);
		return authUser;
	}
	
	public UserVo getUser(int no) {
		return sqlSession.selectOne("User.getUser", no);
	}
	
	public int modify(UserVo userVo) {
		return sqlSession.update("User.modify", userVo);
	}

}
