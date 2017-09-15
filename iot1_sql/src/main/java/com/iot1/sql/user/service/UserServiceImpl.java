package com.iot1.sql.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot1.sql.user.dao.UserDao;
import com.iot1.sql.user.dto.UserInfo;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	public UserInfo login(UserInfo user)
	{
		UserInfo rUser = userDao.selectUser(user);
		if(rUser != null && rUser.getUserPwd().equals(user.getUserPwd()))
		{
			return rUser;
		}
		return null;
	}
	
	public UserInfo selectUser(UserInfo user)
	{
		return null;
	}


	public List<UserInfo> selectUserList(UserInfo user) 
	{
		List<UserInfo> rUserList = userDao.selectUserList(user);
		return rUserList;
	}


	public UserInfo joinUser(UserInfo user) 
	{
		return null;
	}

	
	public UserInfo updateUser(UserInfo user) 
	{
		return null;
	}


	public UserInfo deleteUser(UserInfo user) 
	{
		return null;
	}
}
