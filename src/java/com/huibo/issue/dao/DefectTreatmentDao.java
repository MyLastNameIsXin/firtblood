package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueWorkLogBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueCommentPo;
import com.huibo.issue.po.IssueStatePo;


/**
* <p>Title: 缺陷跟踪管理系统 - DefectTreatmentDao</p>
*
* <p>Description:缺陷处理页面持久接口层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public interface DefectTreatmentDao {
	/**
	 * 	查询当前登陆用户需要处理的缺陷信息
	 * @param bo 
	 * @return 缺陷信息
	 */
	public List<IssueBaseInfoPo> designeeItem(Page page);
	/**
	 * 	缺陷处理页面的项目名下拉
	 * @return 返回当前用户参与的项目名和项目编号
	 */
	public List<InfoIssueProjectPO> queryItemDisposePull(String userId);
	/**
	 * 	根据缺陷编号查询一条缺陷信息
	 * @param bo 缺陷编号 
	 * @return 缺陷记录
	 */
	public IssueBaseInfoPo dispose(XinDefectListsBo bo);
	/**
	 * 查询当前缺陷下有几个反馈信息
	 * @param bo 缺陷编号
	 * @return 反馈条数
	 */
	public IssueCommentPo retroactionNum(XinDefectListsBo bo);
	/**
	 * 	缺陷处理页面加载缺陷反馈信息
	 * @param bo 缺陷编号
	 * @return 缺陷信息
	 */
	public List<IssueCommentPo> seeFeedback(Page page);
	/**
	 * 缺陷处理面板状态下拉
	 * @return 状态名称和状态编号
	 */
	public List<IssueStatePo> imperStatePull();
	/**
	 * 	修改缺陷信息状态已经完成率
	 * @param bo 缺陷信息的状态以及完成率
	 * @return 受影响行数
	 */
	public int modifIssueState(IssueWorkLogBo bo);
	/**
	 * 	新增工时记录
	 * @param bo 耗时，登记日期，缺陷编号
	 * @return 数据库受影响行数
	 */
	public int addManHour(IssueWorkLogBo bo);

}
