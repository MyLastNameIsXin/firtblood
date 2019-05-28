package com.huibo.issue.dao;


import java.util.List;
import java.util.Map;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.UserContrlBo;
import com.huibo.issue.po.UserContrlPo;
/**
* <p>Title: 缺陷跟踪管理系统 - UserContrlDao</p>
*
* <p>Description:用户管理页面持久层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黄媛媛
* @version 1.0
*/
public interface UserContrlDao {

	public List getAllUserContrl(Page page);

	public int addUserContrl(UserContrlBo userContrl);

	public int modifUserContrl(UserContrlBo userContrl);

	public int deleteUserContrl(String[] codes);

	public int verifyUserId(UserContrlBo userContrl);


	
}
