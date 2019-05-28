package com.huibo.issue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueSeverityBo;
import com.huibo.issue.service.IssuePartFiveService;
/**
* <p>Title: 缺陷跟踪管理系统 - IssuePartFiveController</p>
*
* <p>Description:严重程度页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
@Controller
public class IssuePartFiveController {
	@Autowired
	private IssuePartFiveService service;

	@RequestMapping("/serverity/query")
	public Map queryIssueServerity(IssueSeverityBo bo) {
		Map map = null;
		try {
			Page page = new Page(bo);
			map = service.queryIssueServerity(page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/serverity/addOneServerity")
	public Map addOneServerity(IssueSeverityBo bo) {
		Map map = null;
		try {
			map = service.addOneServerity(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/serverity/delete")
	public Map deleteServerityByCode(@RequestParam("severityCodes[]") String[] codes) {
		Map map = null;
		try {
			map = service.deleteServerityByCode(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/serverity/modify")
	public Map modifyServerityByCode(IssueSeverityBo bo,String preCode){
		Map map = null;
		try {
			map = service.modifyServerityByCode(bo,preCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/serverity/modifymodulestate")
	public Map modifyServerityModulestateByCode(IssueSeverityBo bo) {
		Map map = null;
		try {
			map = service.modifyServerityModulestateByCode(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/serverity/checkedInfo")
	public Map checkedInfo(String id) {
		Map map = null;
		try {
			map = service.checkedInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
