package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.issue.bo.IssueCommentBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.service.DefectFeedbackService;

/**
* <p>Title: 缺陷跟踪管理系统 - DefectFeedbackController</p>
*
* <p>Description:缺陷反馈控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Controller
public class DefectFeedbackController {
	@Autowired
	private DefectFeedbackService dService;
	
	/**
	 * 	缺陷反馈页面加载数据
	 * @param bo
	 * @return
	 */
	@RequestMapping("/feedbackItem")
	public Map<String, Object> feedbackItem(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.feedbackItem(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷反馈页面查询项目名下拉
	 * @return
	 */
	@RequestMapping("/feedbackPull")
	public Map<String, Object> feedbackPull(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.feedbackPull();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷反馈面板缺陷状态下拉
	 * @return
	 */
	@RequestMapping("/feedbackStatePull")
	public Map<String, Object> feedbackStatePull(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.feedbackStatePull();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷反馈页面修改缺陷状态，增加反馈信息
	 * @param bo 缺陷编号，缺陷状态，缺陷反馈记录
	 * @return
	 */
	@RequestMapping("/retroaction")
	public Map<String, Object> issueRetroaction(IssueCommentBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.issueRetroaction(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
}
