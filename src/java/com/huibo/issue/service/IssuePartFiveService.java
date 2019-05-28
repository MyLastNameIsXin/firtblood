package com.huibo.issue.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueSeverityBo;
import com.huibo.issue.dao.IssueSeverityDao;
/**
* <p>Title: 缺陷跟踪管理系统 - IssuePartFiveService</p>
*
* <p>Description:严重程度页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
@Service
public class IssuePartFiveService {
	@Autowired
	private IssueSeverityDao dao;
	
	public Map queryIssueServerity(Page page) {
		Map map = new HashMap();
		List list =  dao.queryIssueServerity(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		return map;
	}

	public Map addOneServerity(IssueSeverityBo bo) {
		Map map = new HashMap();
		int result = 0;
		String userId = CommonFunction.getUserFromSession().getUserId();
		Date date = new Date();
		bo.setModifyBy(userId);
		bo.setModifyTime(date);
		bo.setCreateBy(userId);
		bo.setCreateTime(date);
		bo.setModuleState("1");
		result = dao.addOneServerity(bo);
		map.put("result", result);
		return map;
	}

	public Map deleteServerityByCode(String[] codes) {
		int result = 0;
		Map map = new HashMap();
		result = dao.deleteServerityByCode(codes);
		map.put("result", result);
		return map;
	}

	public Map modifyServerityByCode(IssueSeverityBo bo, String preCode) {
		int result = 0;
		Map map = new HashMap();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setModifyBy(userId);
		bo.setModifyTime(new Date());
		result = dao.modifyServerityByCode(bo,preCode);
		map.put("result", result);
		return map;
	}

	public Map modifyServerityModulestateByCode(IssueSeverityBo bo) {
		int result = 0;
		Map map = new HashMap();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setModifyBy(userId);
		bo.setModifyTime(new Date());
		result = dao.modifyServerityModulestateByCode(bo);
		map.put("result", result);
		return map;
	}

	public Map checkedInfo(String id) {
		int result = 0;
		Map map = new HashMap();
		result = dao.checkedInfo(id);
		map.put("data", result>0?false:true);
		return map;
	}
	
	
}
