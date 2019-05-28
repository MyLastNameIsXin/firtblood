package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IIssueAttachBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.po.IIssueAttachPo;
//import com.huibo.issue.bo.XinIssueBaseInfoBo;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueStatePo;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectListsDao</p>
*
* <p>Description:缺陷列表页面持久层接口Dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public interface DefectListsDao {

	public List<InfoIssueProjectPO> queryItemName(String userId);//查询当前用户参与的项目用于生成下拉列表

	public List<IssueStatePo> queryState();//查询状态信息用于生成下拉列表

	public List<InfoIssueProjectPO> queryItemData(String userId);//查询当前用户参与过的项目且是测试人员的

	public List<InfoIssueProjectPO> itemDefect();//查询新增面板中的缺陷分类

	public List<InfoIssueProjectPO> itemDegree();//查询新增面板中的严重程度

	public List<InfoIssueProjectPO> itemPriority();//查询新增修改面板中的优先级下拉

	public List<InfoIssueProjectPO> itemaParentFlaw(XinDefectListsBo bo);//添加面板父级缺陷下拉

	public int addItemDefect(XinDefectListsBo bo);//新增一条缺陷记录

	public List<IssueBaseInfoPo> queryAllData(Page page);//查询当前用户可见的缺陷信息

	public IssueBaseInfoPo forDetails(XinDefectListsBo userID);//查询一条缺陷信息

	public IssueBaseInfoPo queryMaxId();//查询最大的缺陷编号用于填充附件表

	public int addAttach(IIssueAttachBo attachBo);//增加一条附件信息
	/**
	 * 	删除一条缺陷记录
	 * @param integer 缺陷编号
	 * @return 受影响行数
	 */
	public int removeIssueState(Integer issueId);
	/**
	 * 	删除缺陷记录对应的附件记录
	 * @param integer 缺陷编号
	 * @return 受影响行数
	 */
	public int removeAttach(Integer issueId);
	/**
	 * 	查询一条附件信息
	 * @param issueId 缺陷序号
	 * @return
	 */
	public IIssueAttachPo queryAttach(Integer issueId);
	/**
	 * 	查询一条缺陷记录根据附件表ID
	 * @param issueid 附件ID
	 * @return
	 */
	public IIssueAttachPo queryAttachById(Integer issueid);
	/**
	 * 	修改缺陷信息
	 * @param bo 修改后的缺陷信息
	 * @return 数据库受影响行数
	 */
	public int modification(XinDefectListsBo bo);


}
