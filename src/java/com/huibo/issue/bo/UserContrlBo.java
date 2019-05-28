package com.huibo.issue.bo;

import com.huibo.issue.po.UserContrlPo;
/**
* <p>Title: 缺陷跟踪管理系统 - UserContrlBo</p>
*
* <p>Description:用户注册扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黄媛媛
* @version 1.0
*/
public class UserContrlBo extends UserContrlPo{
	private String keyWord;

	public UserContrlBo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserContrlBo(String userId, String userName, String genderId, String mobile, String email, String password) {
		super(userId, userName, genderId, mobile, email, password);
		// TODO Auto-generated constructor stub
	}

	public UserContrlBo(String keyWord) {
		super();
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "UserContrlBo [keyWord=" + keyWord + "]";
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	
	
	
}
