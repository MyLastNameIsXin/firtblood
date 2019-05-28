package com.huibo.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.Mmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueCommentBo;
import com.huibo.issue.bo.IssueWorkLogBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.dao.DefectTreatmentDao;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueCommentPo;
import com.huibo.issue.po.IssueStatePo;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectTreatmentService</p>
*
* <p>Description:缺陷处理页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Service
public class DefectTreatmentService {
	@Autowired
	private DefectTreatmentDao dao;
	/**
	 * 	查询当前登陆用户需要处理的缺陷信息
	 * @param bo 
	 * @return 缺陷信息
	 */
	public Map<String, Object> designeeItem(XinDefectListsBo bo) {
		Page page = new Page(bo);
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		List<IssueBaseInfoPo> list=dao.designeeItem(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 	缺陷处理页面的项目名下拉
	 * @return 返回当前用户参与的项目名和项目编号
	 */
	public Map<String, Object> itemDisposePull() {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		List<InfoIssueProjectPO> list=dao.queryItemDisposePull(userId);
		map.put("list", list);
		return map;
	}
	/**
	 * 	根据缺陷编号查询一条缺陷信息以及当前前缺陷反馈的条数
	 * @param bo 缺陷编号 
	 * @return 缺陷记录
	 */
	public Map<String, Object> dispose(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		IssueBaseInfoPo list=dao.dispose(bo);
		IssueCommentPo num=dao.retroactionNum(bo);
		map.put("list", list);
		map.put("num", num);
		return map;
	}
	/**
	 * 	缺陷处理页面加载缺陷反馈信息
	 * @param bo 缺陷编号
	 * @return 缺陷信息
	 */
	public Map<String, Object> seeFeedback(IssueCommentBo bo) {
		Page page = new Page(bo);
		Map<String, Object> map=new HashMap<>();
		List<IssueCommentPo> list=dao.seeFeedback(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 缺陷处理面板状态下拉
	 * @return 状态名称和状态编号
	 */
	public Map<String, Object> imperStatePull() {
		Map<String, Object> map=new HashMap<>();
		List<IssueStatePo> list=dao.imperStatePull();
		map.put("list", list);
		return map;
	}
	/**
	 * 	缺陷处理确认按钮
	 * @param bo 缺陷编号，缺陷状态，缺陷完成率，登记时间，工时数
	 * @return
	 */
	public Map<String, Object> dealDefects(IssueWorkLogBo bo) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		int row=dao.modifIssueState(bo);
		int row1=dao.addManHour(bo);
		if (row !=0 && row1 !=0) {
			map.put("row", 1);
		}else {
			map.put("row", 0);
		}
		return map;
	}
	
}
