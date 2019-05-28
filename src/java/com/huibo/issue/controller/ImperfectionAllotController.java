package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.service.ImperfectionAllotService;

/**
* <p>Title: 缺陷跟踪管理系统 - ImperfectionAllotController</p>
*
* <p>Description:缺陷分配页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Controller
public class ImperfectionAllotController {
	@Autowired
	private ImperfectionAllotService iaservice;
	/**
	 * 	查询所有当前管理员可见的项目
	 * @return
	 */
	@RequestMapping("/allotIsAdministrator")
	public Map<String, Object> allotIsAdministrator(XinDefectListsBo bo ){
		Map<String, Object> map=new HashMap<>();
		try {
			map=iaservice.allotIsAdministrator(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	加载缺陷分配页面的项目名下拉
	 * @return
	 */
	@RequestMapping("/itemAllotPull")
	public Map<String, Object> itemAllotPull(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=iaservice.itemAllotPull();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 缺陷指派的页面
	 * @param bo 缺陷编号
	 * @return 一条缺陷的详细信息
	 */
	@RequestMapping("/distribute")
	public Map<String, Object> distribute(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=iaservice.distribute(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	分配面板下拉列表
	 * @param bo 当前缺陷编号
	 * @return
	 */
	@RequestMapping("/designate")
	public Map<String, Object> designate(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=iaservice.designate(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	将缺陷指派出去
	 * @param bo 被指派的缺陷编号以及被指派员工编号
	 * @return 是否指派成功
	 */
	@RequestMapping("/assignedTo")
	public Map<String, Object> assignedTo(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=iaservice.assignedTo(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	
}
