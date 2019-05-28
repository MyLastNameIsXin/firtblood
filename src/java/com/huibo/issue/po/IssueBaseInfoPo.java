package com.huibo.issue.po;

import com.bn.javax.po.BasePo;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueBaseInfoPo</p>
*
* <p>Description:缺陷信息实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueBaseInfoPo extends BasePo {
	private Integer issueId;//缺陷序号
	private String	projectId;//项目编号
	private Integer	parentIssueId;//上级缺陷
	private String	issueName;//缺陷名称
	private String	issueDesc;//缺陷描述
	private String	assignee;//指派给
	private String	issueType;//缺陷分类
	private String	issueSeverity;//严重程度
	private String	issuePriority;//优先级
	private String planStartTime;//预计开始日期)
	private String planEndTime;//预计结束日期
	private String actStartTime;//实际开始日期
	private String actEndTime;//实际结束日期
	private String planWorkHours;//预计工时数
	private String actWorkeHours;//实际工时数
	private String assigendTime;//分配时间
	private String doneCondition;//完成要求
	private Integer	doneRatio;//完成百分比
	private String issueState;//缺陷状态
	private String	createBy;//创建人
	private String createTime;//创建时间
	private String	modifBy;//修改人
	private String modifyTime;//修改时间
	private String	issueItem;//项目名称
	private String	issueStateNum;//缺陷状态编号
	private IIssueAttachPo issueAttach;//附件信息
	private String parentIssueName;//父级缺陷名称
	private String projectName;//项目名称
	public IssueBaseInfoPo(Integer issueId, String projectId, Integer parentIssueId, String issueName, String issueDesc,
			String assignee, String issueType, String issueSeverity, String issuePriority, String planStartTime,
			String planEndTime, String actStartTime, String actEndTime, String planWorkHours, String actWorkeHours,
			String assigendTime, String doneCondition, Integer doneRatio, String issueState, String createBy,
			String createTime, String modifBy, String modifyTime, String issueItem) {
		super();
		this.issueId = issueId;
		this.projectId = projectId;
		this.parentIssueId = parentIssueId;
		this.issueName = issueName;
		this.issueDesc = issueDesc;
		this.assignee = assignee;
		this.issueType = issueType;
		this.issueSeverity = issueSeverity;
		this.issuePriority = issuePriority;
		this.planStartTime = planStartTime;
		this.planEndTime = planEndTime;
		this.actStartTime = actStartTime;
		this.actEndTime = actEndTime;
		this.planWorkHours = planWorkHours;
		this.actWorkeHours = actWorkeHours;
		this.assigendTime = assigendTime;
		this.doneCondition = doneCondition;
		this.doneRatio = doneRatio;
		this.issueState = issueState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifBy = modifBy;
		this.modifyTime = modifyTime;
		this.issueItem = issueItem;
	}
	@Override
	public String toString() {
		return "IssueBaseInfoPo [issueId=" + issueId + ", projectId=" + projectId + ", parentIssueId=" + parentIssueId
				+ ", issueName=" + issueName + ", issueDesc=" + issueDesc + ", assignee=" + assignee + ", issueType="
				+ issueType + ", issueSeverity=" + issueSeverity + ", issuePriority=" + issuePriority
				+ ", planStartTime=" + planStartTime + ", planEndTime=" + planEndTime + ", actStartTime=" + actStartTime
				+ ", actEndTime=" + actEndTime + ", planWorkHours=" + planWorkHours + ", actWorkeHours=" + actWorkeHours
				+ ", assigendTime=" + assigendTime + ", doneCondition=" + doneCondition + ", doneRatio=" + doneRatio
				+ ", issueState=" + issueState + ", createBy=" + createBy + ", createTime=" + createTime + ", modifBy="
				+ modifBy + ", modifyTime=" + modifyTime + ", issueItem=" + issueItem + "]";
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getParentIssueId() {
		return parentIssueId;
	}
	public void setParentIssueId(Integer parentIssueId) {
		this.parentIssueId = parentIssueId;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getIssueDesc() {
		return issueDesc;
	}
	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getIssueSeverity() {
		return issueSeverity;
	}
	public void setIssueSeverity(String issueSeverity) {
		this.issueSeverity = issueSeverity;
	}
	public String getIssuePriority() {
		return issuePriority;
	}
	public void setIssuePriority(String issuePriority) {
		this.issuePriority = issuePriority;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getActStartTime() {
		return actStartTime;
	}
	public void setActStartTime(String actStartTime) {
		this.actStartTime = actStartTime;
	}
	public String getActEndTime() {
		return actEndTime;
	}
	public void setActEndTime(String actEndTime) {
		this.actEndTime = actEndTime;
	}
	public String getPlanWorkHours() {
		return planWorkHours;
	}
	public void setPlanWorkHours(String planWorkHours) {
		this.planWorkHours = planWorkHours;
	}
	public String getActWorkeHours() {
		return actWorkeHours;
	}
	public void setActWorkeHours(String actWorkeHours) {
		this.actWorkeHours = actWorkeHours;
	}
	public String getAssigendTime() {
		return assigendTime;
	}
	public void setAssigendTime(String assigendTime) {
		this.assigendTime = assigendTime;
	}
	public String getDoneCondition() {
		return doneCondition;
	}
	public void setDoneCondition(String doneCondition) {
		this.doneCondition = doneCondition;
	}
	public Integer getDoneRatio() {
		return doneRatio;
	}
	public void setDoneRatio(Integer doneRatio) {
		this.doneRatio = doneRatio;
	}
	public String getIssueState() {
		return issueState;
	}
	public void setIssueState(String issueState) {
		this.issueState = issueState;
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
	public String getModifBy() {
		return modifBy;
	}
	public void setModifBy(String modifBy) {
		this.modifBy = modifBy;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getIssueItem() {
		return issueItem;
	}
	public void setIssueItem(String issueItem) {
		this.issueItem = issueItem;
	}
	public IssueBaseInfoPo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public String getIssueStateNum() {
		return issueStateNum;
	}
	public void setIssueStateNum(String issueStateNum) {
		this.issueStateNum = issueStateNum;
	}
	public IIssueAttachPo getIssueAttach() {
		return issueAttach;
	}
	public void setIssueAttach(IIssueAttachPo issueAttach) {
		this.issueAttach = issueAttach;
	}
	public String getParentIssueName() {
		return parentIssueName;
	}
	public void setParentIssueName(String parentIssueName) {
		this.parentIssueName = parentIssueName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
}
