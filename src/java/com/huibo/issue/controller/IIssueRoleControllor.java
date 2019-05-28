package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huibo.issue.bo.IIssueRoleBO;
import com.huibo.issue.po.IIssueRolePO;
import com.huibo.issue.service.IIssueRoleService;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueRoleControllor</p>
*
* <p>Description:角色分配控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
@Controller
@RequestMapping("/view/issue/lili")
public class IIssueRoleControllor {

	@Autowired
	private IIssueRoleService iIssueRoleService;
	
	/**
	 * 获取项目成员信息
	 * @param protect_ID
	 * @return
	 */
	@RequestMapping("/getDate")
	public Map getDate(@RequestParam("protect_ID") String protect_ID) {
		Map map = new HashMap();
		try {
			List<IIssueRoleBO> list = iIssueRoleService.getDate(protect_ID);
			map.put("rows",list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	
	@RequestMapping("/addRoleInfo")
	public Map addRoleInfo(IIssueRolePO po) {
		Map map = new HashMap();
		try {
			Integer row = iIssueRoleService.addRoleInfo(po);
			map.put("row",row);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	@RequestMapping("/removeRoleInfo")
	public Map removeRoleInfo(IIssueRolePO po) {
		Map map = new HashMap();
		try {
			Integer row = iIssueRoleService.removeRoleInfo(po);
			map.put("row",row);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	@RequestMapping("/findUserName")
	public Map findUserName() {
		Map map = new HashMap();
		try {
			List<IIssueRoleBO> list = iIssueRoleService.findUserName();
			map.put("rows",list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	
	@RequestMapping("/findRoleName")
	public Map findRoleName() {
		Map map = new HashMap();
		try {
			List<IIssueRoleBO> list = iIssueRoleService.findRoleName();
			map.put("rows",list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
}
