package com.iot1.sql.user.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.iot1.sql.user.dto.UserInfo;

@Service
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao 
{
	
	public UserInfo selectUser(UserInfo user)
	{
		return this.getSqlSession().selectOne("user.SELECT_USER", user);
	}

	
	public List<UserInfo> selectUserList(UserInfo user) 
	{	
		return this.getSqlSession().selectList("user.LIST_USER", user);
	}

	
	public int insertUser(UserInfo user) 
	{	
		return this.getSqlSession().insert("user.INSERT_USER", user);
	}

	
	public int updateUser(UserInfo user)
	{
		return this.getSqlSession().update("user.UPDATE_USER", user);
	}

	
	public int deleteUser(UserInfo user)
	{
		return this.getSqlSession().delete("user.DELETE_USER", user);
	}
	
}
