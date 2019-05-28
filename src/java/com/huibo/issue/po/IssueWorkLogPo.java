package com.huibo.issue.po;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueWorkLogPo</p>
*
* <p>Description:工时记录实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueWorkLogPo {
	private Integer logId;//记录序号
	private Integer issueId;//缺陷序号
	private String workHours;//工时数
	private String	logDesc;//记录描述
	private String	logDate;//登记时间
	private String	logState;//当前状态
	private String createBy;//创建人
	private String	createTime;//创建时间
	private String	modifyBy;//修改人
	private String	moifyTime;//修改时间
	private Integer DefectTime;//当前缺陷一共耗时
	private	String	userName;//创建人名字
	private String	projectName;//项目名称
	private String issueName;//缺陷名称
	public IssueWorkLogPo() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public IssueWorkLogPo(Integer logId, Integer issueId, String workHours, String logDesc, String logDate,
			String logState, String createBy, String createTime, String modifyBy, String moifyTime,
			Integer defectTime) {
		super();
		this.logId = logId;
		this.issueId = issueId;
		this.workHours = workHours;
		this.logDesc = logDesc;
		this.logDate = logDate;
		this.logState = logState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.moifyTime = moifyTime;
		DefectTime = defectTime;
	}

	@Override
	public String toString() {
		return "IssueWorkLogPo [logId=" + logId + ", issueId=" + issueId + ", workHours=" + workHours + ", logDesc="
				+ logDesc + ", logDate=" + logDate + ", logState=" + logState + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", moifyTime=" + moifyTime + ", DefectTime="
				+ DefectTime + "]";
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogState() {
		return logState;
	}

	public void setLogState(String logState) {
		this.logState = logState;
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

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getMoifyTime() {
		return moifyTime;
	}

	public void setMoifyTime(String moifyTime) {
		this.moifyTime = moifyTime;
	}

	public Integer getDefectTime() {
		return DefectTime;
	}

	public void setDefectTime(Integer defectTime) {
		DefectTime = defectTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	
	
	
}
