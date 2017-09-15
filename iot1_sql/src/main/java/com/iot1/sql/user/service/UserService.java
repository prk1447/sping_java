package com.iot1.sql.user.service;

import java.util.List;

import com.iot1.sql.user.dto.UserInfo;

public interface UserService 
{
	public UserInfo login(UserInfo user);
	
	public UserInfo selectUser(UserInfo user);
	
	public List<UserInfo> selectUserList(UserInfo user);
	
	public UserInfo joinUser(UserInfo user);
	
	public UserInfo updateUser(UserInfo user);
	
	public UserInfo deleteUser(UserInfo user);
}
