package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.ProjectRolesBo;
import com.huibo.issue.po.IssueRolePo;
import com.huibo.issue.po.IssueStatePo;
/**
* <p>Title: 缺陷跟踪管理系统 - ProjectDao</p>
*
* <p>Description:角色信息页面持久层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public interface ProjectDao {

	public List<IssueRolePo> queryAllRole(Page page);

	public List<IssueStatePo> operability();
																
	public int addRole(String roleCode, String roleName,String userId);

	public int addRele(String roleCode,String[] stateCode);
	
	public int removeRole(String[] roleCode);//删除角色

	public int removeRele(String[] roleCode);//删除角色关联表

	public int starFor(String roleCode, String moduleState, String userId);//启用禁用角色信息

	public List<IssueRolePo> queryRole(ProjectRolesBo bo);//查询一条角色信息用于填充修改面板

	public int modifierRole(String roleCode, String roleName, String userId);//修改角色信息

	public int modifRele(String roleCode, String[] stateCode);//修改角色信息的关联表

	public int erifyRoleCode(ProjectRolesBo bo);//验证用户编号是否存在

	public int erifyRoleName(ProjectRolesBo bo);//验证用户角色是否已经存在

}
