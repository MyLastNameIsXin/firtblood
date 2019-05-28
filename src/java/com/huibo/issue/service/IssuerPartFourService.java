package com.huibo.issue.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.dao.IssuePriorityDao;
import com.huibo.issue.service.IssuerPartFourService;
/**
* <p>Title: 缺陷跟踪管理系统 - IssuerPartFourService</p>
*
* <p>Description:优先级页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
@Service
public class IssuerPartFourService{
	@Autowired
	private IssuePriorityDao issuePriorityDao;

	public Map queryIssuePriority(Page page) {
		Map map = new HashMap();
		List list = issuePriorityDao.queryIssuePriority(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		return map;
	}

	public Map modifyIssuePriorityByCode(String prePriorityCode, String priorityCode, String priorityDesc) {
		int result = 0;
		Map map = new HashMap();
		if(prePriorityCode == null || priorityCode == null ) {
			map.put("result", 0);
			return map;
		}
		if(prePriorityCode.isEmpty() || priorityCode.isEmpty()) {
			map.put("result", 0);
			return map;
		}
		String userId = CommonFunction.getUserFromSession().getUserId();
		Date date = new Date();
		result = issuePriorityDao.modifyIssuePriorityByCode(prePriorityCode,priorityCode,priorityDesc,userId,date);
		map.put("result", result);
		return map;
	}

	public Map deleteIssuePriorityByCodes(String[] codes) {
		int result = 0;
		Map map = new HashMap();
		result = issuePriorityDao.deleteIssuePriorityByCodes(codes);
		map.put("result", result);
		return map;
	}

	public Map modifyIssueModuleStateByCode(String priorityCode, String moduleState) {
		int result = 0;
		Map map = new HashMap();
		result = issuePriorityDao.modifyIssueModuleStateByCode(priorityCode,moduleState);
		map.put("result", result);
		return map;
	}

	public Map addOnePriority(String priorityCode, String priorityDesc) {
		String userId = CommonFunction.getUserFromSession().getUserId();
		Date date = new Date();
		int result = 0;
		Map map = new HashMap();
		result = issuePriorityDao.addOnePriority(priorityCode,priorityDesc,userId,date);
		map.put("result", result);
		return map;
	}

	public Map checkedInfo(String id) {
		Map map = new HashMap();
		int result = 0;
		result = issuePriorityDao.checkedInfo(id);
		if(result>0) {
			map.put("data",false);
		}else {
			map.put("data",true);
		}
		return map;
	}

}
