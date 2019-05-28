package com.huibo.issue.bo;

import com.huibo.issue.po.IssueWorkLogPo;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueWorkLogBo</p>
*
* <p>Description:工时记录扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueWorkLogBo extends IssueWorkLogPo {
	private String projectId;//缺陷状态
	private String roleCode;//完成率
	private String userId;//用户编号
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
