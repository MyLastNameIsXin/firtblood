package com.huibo.issue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huibo.issue.bo.IIssueRoleBO;
import com.huibo.issue.po.IIssueRolePO;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueRoleDao</p>
*
* <p>Description:角色分配持久接口层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
public interface IIssueRoleDao {

	public List<IIssueRoleBO> getDate(@Param("pid") String pid);

	public List<IIssueRoleBO> getDateByRoleId(@Param("pid") String pid,@Param("uid") String uid);

	public Integer addRoleInfo(IIssueRolePO po);

	public Integer removeRoleInfo(IIssueRolePO po);

	public List<IIssueRoleBO> findUserName();

	public List<IIssueRoleBO> findRoleName();

}
