package com.huibo.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.bsst.ezwork.javax.util.General;
import com.huibo.issue.bo.UserContrlBo;
import com.huibo.issue.dao.UserContrlDao;
import com.huibo.issue.po.UserContrlPo;
/**
* <p>Title: 缺陷跟踪管理系统 - UserContrlService</p>
*
* <p>Description:用户管理页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黄媛媛
* @version 1.0
*/
@Service
public class UserContrlService {
	@Autowired
	private  UserContrlDao  userContrlDao;

	
	/**
	 * 查询用户管理信息
	 * @param userContrl
	 * @return
	 */
	public Map getAllUserContrl(Page page) {
		
		Map map = new HashMap();
		List list = userContrlDao.getAllUserContrl(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		return map;
	}

	/**
	 *新增用户信息 
	 * @param userContrl
	 * @return
	 */
	public Map addUserContrl(UserContrlBo userContrl) {
		Map map = new HashMap();
		userContrl.setPassword(General.getEncryptString(userContrl.getPassword()));
		map.put("result", userContrlDao.addUserContrl(userContrl));
		return map;
	}

	public Map modifUserContrl(UserContrlBo userContrl) {
		Map map = new HashMap();
		userContrl.setPassword(General.getEncryptString(userContrl.getPassword()));
		map.put("result", userContrlDao.modifUserContrl(userContrl));
		return map;
	}

	public Map deleteUserContrl(String[] codes) {
		Map map = new HashMap();
		map.put("result", userContrlDao.deleteUserContrl(codes));
		return map;
	}

	public Map verifyUserId(UserContrlBo userContrl) {
		Map map = new HashMap();
		map.put("resultCode", userContrlDao. verifyUserId(userContrl)==1?0:1);
		return map;
	}




}
