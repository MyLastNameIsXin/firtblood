package com.huibo.issue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssuePriorityBo;
import com.huibo.issue.controller.IssuerPartFourController;
import com.huibo.issue.service.IssuerPartFourService;

/**
* <p>Title: 缺陷跟踪管理系统 - IssuerPartFourController</p>
*
* <p>Description:优先级页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
@Controller
public class IssuerPartFourController{
	@Autowired
	private IssuerPartFourService issuerPartFourService;
	
	@RequestMapping("/priority/query")
	public Map queryIssuePriority(IssuePriorityBo issuePriorityBO) {
		Map map  = null;
		try {
			Page page = new Page(issuePriorityBO);
			map = issuerPartFourService.queryIssuePriority(page);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/priority/modify")
	public Map modifyIssuePriorityByCode(String prePriorityCode, String priorityCode, String priorityDesc) {
		Map map  = null;
		try {
			map = issuerPartFourService.modifyIssuePriorityByCode(prePriorityCode,priorityCode,priorityDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/priority/delete")
	public Map deleteIssuePriorityByCodes(@RequestParam("priorityCodes[]") String[] codes) {
		Map map  = null;
		try {
			map = issuerPartFourService.deleteIssuePriorityByCodes(codes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@RequestMapping("/priority/modifymodulestate")
	public Map modifyIssueModuleStateByCode(String priorityCode, String moduleState) {
		Map map  = null;
		try {
			map = issuerPartFourService.modifyIssueModuleStateByCode(priorityCode,moduleState);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@RequestMapping("/priority/addOnePriprity")
	public Map addOnePriority(String priorityCode, String priorityDesc) {
		Map map  = null;
		try {
			map = issuerPartFourService.addOnePriority(priorityCode,priorityDesc);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/priority/checkedInfo")
	public Map checkedInfo(String id) {
		Map map  = null;
		try {
			map = issuerPartFourService.checkedInfo(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}

