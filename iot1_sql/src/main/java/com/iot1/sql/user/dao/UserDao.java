package com.iot1.sql.user.dao;

import java.util.List;

import com.iot1.sql.user.dto.UserInfo;

public interface UserDao
{
	UserInfo selectUser(UserInfo user);
	
	List<UserInfo> selectUserList(UserInfo user);
	
	int insertUser(UserInfo user);
	
	int updateUser(UserInfo user);
	
	int deleteUser(UserInfo user);
}
