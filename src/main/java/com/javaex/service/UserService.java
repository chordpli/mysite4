package com.javaex.service;

import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	private UserDao userDao;
	
	public int join() {
		System.out.println("UserService > join()");
		return userDao.join();
	}

}
