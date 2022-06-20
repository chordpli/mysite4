package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("UserService > join()");
		return userDao.join(userVo);
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login()");
		return userDao.login(userVo);
		
	}
	
	public UserVo getUser(int no) {
		System.out.println("UserService > getUser()");
		return userDao.getUser(no);
	}
	
	public int modify(UserVo userVo) {
		System.out.println("UserService > modify()");
		return userDao.modify(userVo);
	}

}
