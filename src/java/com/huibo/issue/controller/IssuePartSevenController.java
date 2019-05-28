package com.huibo.issue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueStateBo;
import com.huibo.issue.service.IssuePartSevenService;
/**
* <p>Title: 缺陷跟踪管理系统 - IssuePartSevenController</p>
*
* <p>Description:缺陷状态页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
@Controller
public class IssuePartSevenController {
	
	@Autowired
	private IssuePartSevenService service;

	@RequestMapping("/state/query")
	public Map queryIssueState(IssueStateBo bo) {
		Map map = null;
		try {
			Page page = new Page(bo);
			map = service.queryIssueState(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/state/addOneInfo")
	public Map addOneState(IssueStateBo bo) {
		Map map = null;
		try {
			map = service.addOneState(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/state/delete")
	public Map deleteStateByCode(@RequestParam("stateCodes[]") String[] codes) {
		Map map = null;
		try {
			map = service.deleteStateByCode(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/state/modify")
	public Map modifyStateByCode(IssueStateBo bo,String preCode){
		Map map = null;
		try {
			map = service.modifyStateByCode(bo,preCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/state/modifymodulestate")
	public Map modifyStateModulestateByCode(IssueStateBo bo) {
		Map map = null;
		try {
			map = service.modifyStateModulestateByCode(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	 
	@RequestMapping("/state/checkedInfo")
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
