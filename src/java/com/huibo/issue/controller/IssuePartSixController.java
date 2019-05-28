package com.huibo.issue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueTypeBo;
import com.huibo.issue.service.IssuePartSixService;


/**
* <p>Title: 缺陷跟踪管理系统 - IssuePartSixController</p>
*
* <p>Description:缺陷分类页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
@Controller
public class IssuePartSixController {

	@Autowired
	private IssuePartSixService service;

	@RequestMapping("/type/query")
	public Map queryIssueType(IssueTypeBo bo) {
		Map map = null;
		try {
			Page page = new Page(bo);
			map = service.queryIssueType(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/type/addOneInfo")
	public Map addOnetype(IssueTypeBo bo) {
		Map map = null;
		try {
			map = service.addOnetype(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/type/delete")
	public Map deleteTypeByCode(@RequestParam("typeCodes[]") String[] codes) {
		Map map = null;
		try {
			map = service.deleteTypeByCode(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/type/modify")
	public Map modifyTypeByCode(IssueTypeBo bo,String preCode){
		Map map = null;
		try {
			map = service.modifyTypeByCode(bo,preCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/type/modifymodulestate")
	public Map modifyTypeModulestateByCode(IssueTypeBo bo) {
		Map map = null;
		try {
			map = service.modifyTypeModulestateByCode(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/type/checkedInfo")
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
