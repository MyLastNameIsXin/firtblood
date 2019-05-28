package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueCommentBo;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueStatePo;
import com.huibo.issue.po.IssueWorkLogPo;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectFeedbackDao</p>
*
* <p>Description:缺陷反馈持久层接口类dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public interface DefectFeedbackDao {
	/**
	 * 	查询当前用户在缺陷反馈页面可见的缺陷信息
	 * @param page
	 * @return
	 */
	public List<IssueBaseInfoPo> feedbackItem(Page page);
	/**
	 * 	缺陷反馈页面加载项目名下拉以管理员身份参加的
	 * @param userId 当前用户的员工编号
	 * @return 项目名以及项目编号
	 */
	public List<InfoIssueProjectPO> feedbackPull(String userId);
	/**
	 * 	缺陷反馈面板缺陷状态下拉
	 * @return
	 */
	public List<IssueStatePo> feedbackStatePull();
	/**
	 * 	缺陷反馈页面修改当前缺陷状态
	 * @param bo 当前缺陷状态，缺陷一共耗时
	 * @return
	 */
	public int modifIssueRetroaction(IssueCommentBo bo);
	/**
	 * 	查询当前缺陷记录的所有工时记录
	 * @param bo 缺陷编号
	 * @return 当前缺陷处理一共花费了多少时间
	 */
	public IssueWorkLogPo queryWorkLog(IssueCommentBo bo);
	/**
	 * 	新增一条反馈记录
	 * @param bo
	 * @return
	 */
	public int addRetroaction(IssueCommentBo bo);

}
