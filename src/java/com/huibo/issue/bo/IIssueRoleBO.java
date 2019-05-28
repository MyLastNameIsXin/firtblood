package com.huibo.issue.bo;

import com.huibo.issue.po.IIssueRolePO;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueRoleBO</p>
*
* <p>Description:角色信息扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
public class IIssueRoleBO extends IIssueRolePO {

	/**
	 * 
	 */
	public IIssueRoleBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param protectId
	 * @param userId
	 * @param userName
	 * @param roleCode
	 * @param roleName
	 * @param createBy
	 * @param createTime
	 */
	public IIssueRoleBO(String protectId, String userId, String userName, String roleCode, String roleName,
			String createBy, String createTime) {
		super(protectId, userId, userName, roleCode, roleName, createBy, createTime);
		// TODO Auto-generated constructor stub
	}

	
	
	
}
