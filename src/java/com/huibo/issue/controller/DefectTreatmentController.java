package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.issue.bo.IssueCommentBo;
import com.huibo.issue.bo.IssueWorkLogBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.service.DefectTreatmentService;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectTreatmentController</p>
*
* <p>Description:缺陷处理页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Controller
public class DefectTreatmentController {
	@Autowired
	private DefectTreatmentService service;
	/**
	 * 	查询当前登陆用户需要处理的缺陷信息
	 * @return 缺陷信息
	 */
	@RequestMapping("/designeeItem")
	public Map<String, Object> designeeItem(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=service.designeeItem(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷处理页面的项目名下拉
	 * @return 返回当前用户参与的项目名和项目编号
	 */
	@RequestMapping("/itemDisposePull")
	public Map<String, Object> itemDisposePull(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=service.itemDisposePull();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	根据缺陷编号查询一条缺陷信息
	 * @param bo 缺陷编号 
	 * @return 缺陷记录
	 */
	@RequestMapping("/dispose")
	public Map<String, Object> dispose(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=service.dispose(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷处理页面加载缺陷反馈信息
	 * @param bo 缺陷编号
	 * @return 缺陷信息
	 */
	@RequestMapping("/seeFeedback")
	public Map<String, Object> seeFeedback(IssueCommentBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=service.seeFeedback(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	} 
	
	/**
	 * 缺陷处理面板状态下拉
	 * @return 状态名称和状态编号
	 */
	@RequestMapping("/imperStatePull")
	public Map<String, Object> imperStatePull(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=service.imperStatePull();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷处理确认按钮
	 * @param bo 缺陷编号，缺陷状态，缺陷完成率，登记时间，工时数
	 * @return
	 */
	@RequestMapping("/dealDefects")
	public Map<String, Object> dealDefects(IssueWorkLogBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=service.dealDefects(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
}
