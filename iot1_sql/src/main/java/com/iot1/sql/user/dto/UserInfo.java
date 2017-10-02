package com.iot1.sql.user.dto;

import org.springframework.stereotype.Component;

@Component
public class UserInfo 
{
	private String userId;
	private String userPwd;
	private String userName;
	private int age;
	private String address;
	private String hp1;
	private String hp2;
	private String hp3;
	private int departnum;
	private String userRoleLevel;
	private String gender;
	private int userNo;
	
	@Override
	public String toString() 
	{
		return "UserInfo [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", age=" + age
				+ ", address=" + address + ", hp1=" + hp1 + ", hp2=" + hp2 + ", hp3=" + hp3 + ", departnum=" + departnum
				+ ", userRoleLevel=" + userRoleLevel + ", gender=" + gender + ", userNo=" + userNo + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHp1() {
		return hp1;
	}

	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}

	public String getHp2() {
		return hp2;
	}

	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}

	public String getHp3() {
		return hp3;
	}

	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}

	public int getDepartnum() {
		return departnum;
	}

	public void setDepartnum(int departnum) {
		this.departnum = departnum;
	}

	public String getUserRoleLevel() {
		return userRoleLevel;
	}

	public void setUserRoleLevel(String userRoleLevel) {
		this.userRoleLevel = userRoleLevel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
}
