package com.huibo.issue.po;

import com.bn.javax.po.BasePo;
/**
* <p>Title: 缺陷跟踪管理系统 - UserContrlPo</p>
*
* <p>Description:用户信息实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黄媛媛
* @version 1.0
*/
public class UserContrlPo extends BasePo{
	private String userId;
	private String userName;
	private String	genderId;
	private String	mobile;
	private String	email;
	private String	password;
	public UserContrlPo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserContrlPo(String userId, String userName, String genderId, String mobile, String email, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.genderId = genderId;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGenderId() {
		return genderId;
	}
	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserContrlPo [userId=" + userId + ", userName=" + userName + ", genderId=" + genderId + ", mobile="
				+ mobile + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
