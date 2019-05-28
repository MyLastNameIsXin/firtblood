package com.huibo.issue.po;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueRolePO</p>
*
* <p>Description:项目角色分配实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
public class IIssueRolePO {
	private String protectId;
	private String userId;
	private String userName;
	private String roleCode;
	private String roleName;
	private String createBy;
	private String createTime;
	/**
	 * 
	 */
	public IIssueRolePO() {
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
	public IIssueRolePO(String protectId, String userId, String userName, String roleCode, String roleName,
			String createBy, String createTime) {
		super();
		this.protectId = protectId;
		this.userId = userId;
		this.userName = userName;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.createBy = createBy;
		this.createTime = createTime;
	}
	public String getProtectId() {
		return protectId;
	}
	public void setProtectId(String protectId) {
		this.protectId = protectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "IIssueRolePO [protectId=" + protectId + ", userId=" + userId + ", userName=" + userName + ", roleCode="
				+ roleCode + ", roleName=" + roleName + ", createBy=" + createBy + ", createTime=" + createTime + "]";
	}
	
	
	
}
