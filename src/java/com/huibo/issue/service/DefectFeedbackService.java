package com.huibo.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueCommentBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.dao.DefectFeedbackDao;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueStatePo;
import com.huibo.issue.po.IssueWorkLogPo;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectFeedbackService</p>
*
* <p>Description:缺陷反馈业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Service
public class DefectFeedbackService {
	@Autowired
	private DefectFeedbackDao dao;

	/**
	 * 	缺陷反馈页面
	 * @param bo 分页所需参数
	 * @return
	 */
	public Map<String, Object> feedbackItem(XinDefectListsBo bo) {
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		Map<String, Object> map=new HashMap<>();
		Page page=new Page(bo);
		List<IssueBaseInfoPo> list=dao.feedbackItem(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 	缺陷反馈页面加载项目名下拉
	 * @return
	 */
	public Map<String, Object> feedbackPull() {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		List<InfoIssueProjectPO> list=dao.feedbackPull(userId);
		map.put("list", list);
		return map;
	}
	/**
	 * 	缺陷反馈面板缺陷状态下拉
	 * @return
	 */
	public Map<String, Object> feedbackStatePull() {
		Map<String, Object> map=new HashMap<>();
		List<IssueStatePo> list=dao.feedbackStatePull();
		map.put("list", list);
		return map;
	}
	/**
	 * 	缺陷反馈页面修改缺陷状态，增加反馈信息
	 * @param bo 缺陷编号，缺陷状态，缺陷反馈记录
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)//当数据增加发生错误时进行数据回滚
	public Map<String, Object> issueRetroaction(IssueCommentBo bo) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		if (bo.getProjectId().equals("04")){
			//当管理员将缺陷状态更改为已关闭时，查询当前缺陷下的所有工时记录，并相加得出最终耗时
			IssueWorkLogPo DefectTime=dao.queryWorkLog(bo);
			bo.setTimeElapsed(DefectTime.getDefectTime());
		}
		int modif=dao.modifIssueRetroaction(bo);//修改当前缺陷的状态
		int row=dao.addRetroaction(bo);//新增一条反馈记录
		if (modif != 0 && row!=0) {
			map.put("row", 1);
		}else {
			map.put("row", 0);
		}
		return map;
	}
	
}
