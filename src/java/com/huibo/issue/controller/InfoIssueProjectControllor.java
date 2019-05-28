package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.issue.bo.InfoIssueProjectBO;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.service.InfoIssueProjectService;
/**
* <p>Title: 缺陷跟踪管理系统 - InfoIssueProjectControllor</p>
*
* <p>Description:项目管理页面控制器controller</p>
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
public class InfoIssueProjectControllor {
	@Autowired
	private InfoIssueProjectService infoIssueProjectService;
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@RequestMapping("/findProtectStartInfo")
	public Map findProtectStartInfo(String id) {
		Map map = new HashMap();
		try {
			List<InfoIssueProjectBO> list = infoIssueProjectService.findProtectStartInfo(id);
			map.put("rows", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	
	/**
	 * 根据pPrijectId查询项目
	 * @param id
	 * @return
	 */
	@RequestMapping("/findProtectInfo")
	public Map findProtectInfo(String id) {
		Map map = new HashMap();
		try {
			List<InfoIssueProjectBO> list = infoIssueProjectService.findProtectInfo(id);
			map.put("rows", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	/**
	 * 根据protecId查询项目
	 * @param id
	 * @return
	 */
	/*@RequestMapping("/findFatherProtectInfo")
	public Map findFatherProtectInfo(String id) {
		System.out.println(id);
		Map map = new HashMap();
		try {
			InfoIssueProjectBO bo = infoIssueProjectService.findFatherProtectInfo(id);
			map.put("row", bo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}*/
	
	/**
	 * 根据ID添加项目
	 * @param po
	 * @return
	 */
	@RequestMapping("/addProtectInfo")
	public Map addProtectInfo(InfoIssueProjectPO po) {
		Map map = new HashMap();
		try {
			Integer row = infoIssueProjectService.addProtectInfo(po);
			map.put("row", row);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	
	/**
	 * 根据ID修改项目
	 * @param po
	 * @return
	 */
	@RequestMapping("/modifyProtectInfo")
	public Map modifyProtectInfo(InfoIssueProjectPO po) {
		Map map = new HashMap();
		try {
			Integer  rows= infoIssueProjectService.modifyProtectInfo(po);
			map.put("rows", rows);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	/**
	 * 修改状态
	 * @param po
	 */
	/*@RequestMapping("/modifyProtectInfoState")
	public void modifyProtectInfoState(InfoIssueProjectPO po) {
		try {
			String pid = po.getpProjectId();
			InfoIssueProjectPO bo = (InfoIssueProjectPO)infoIssueProjectService.findFatherProtectInfo(pid);
			bo.setProjectState(po.getProjectState());
			infoIssueProjectService.modifyProtectInfo(bo);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	
	
	/**
	 * 
	 */
	/*@RequestMapping("findProtectId")
	public Map findProtectId(String id) {
		Map map = new HashMap();
		try {
			Integer row = infoIssueProjectService.findProtectId(id);
			map.put("row", row);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}*/
}
