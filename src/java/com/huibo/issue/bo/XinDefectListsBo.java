package com.huibo.issue.bo;

import com.huibo.issue.po.IssueBaseInfoPo;
/**
* <p>Title: 缺陷跟踪管理系统 - XinDefectListsBo</p>
*
* <p>Description:缺陷记录扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class XinDefectListsBo  extends IssueBaseInfoPo{
	private String startTime;//查询中计划开始时间
	private String endTime;//查询中的计划结束时间
	private String userId;//用户id
	private String issueUseId;
	
	public String getIssueUseId() {
		return issueUseId;
	}
	public void setIssueUseId(String issueUseId) {
		this.issueUseId = issueUseId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
