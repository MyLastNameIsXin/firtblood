package com.huibo.issue.bo;

import com.huibo.issue.po.IssueCommentPo;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueCommentBo</p>
*
* <p>Description:评论记录扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueCommentBo extends IssueCommentPo {
	public String projectId;//缺陷反馈提交时的缺陷状态
	public Integer timeElapsed;//当前缺陷总共耗时
	public String userId;//当前用户编号
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(Integer timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
