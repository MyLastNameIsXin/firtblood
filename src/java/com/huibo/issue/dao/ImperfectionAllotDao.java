package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.UserContrlPo;
/**
* <p>Title: 缺陷跟踪管理系统 - ImperfectionAllotDao</p>
*
* <p>Description:缺陷分配页面持久层接口dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public interface ImperfectionAllotDao {
	/**
	 * 	查询当前管理员可见的缺陷信息
	 * @param page 当前登陆人员的用户编号
	 * @return 返回可见的缺陷信息
	 */
	public List<IssueBaseInfoPo> allotIsAdministrator(Page page);
	/**
	 * 	查询当前用户可见缺陷下拉
	 * @param userId 用户id
	 * @return	缺陷名称集合
	 */
	public List<InfoIssueProjectPO> itemAllotPull(String userId);
	/**
	 * 	查询一条缺陷的详细信息
	 * @param bo 当前缺陷的编号
	 * @return 返回一条缺陷的详细信息
	 */
	public IssueBaseInfoPo distribute(XinDefectListsBo bo);
	/**
	 * 	缺陷分配面板下来框
	 * @param bo 当前缺陷的项目编号
	 * @return 返回参与过当前项目的员工编号和姓名
	 */
	public List<UserContrlPo> queryUserName(XinDefectListsBo bo);
	/**
	 * 	将缺陷指派出去
	 * @param bo 被指派的缺陷编号以及被指派员工编号
	 * @return 是否指派成功
	 */
	public int assignedTo(XinDefectListsBo bo);

}
