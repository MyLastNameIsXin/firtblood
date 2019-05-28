package com.huibo.issue.po;

import java.util.Date;

import com.bn.javax.po.BasePo;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueSeverityPo</p>
*
* <p>Description:严重程度实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public class IssueSeverityPo extends BasePo{
	private String severityCode;
	private String severityDesc;
	private String moduleState;
	private String createBy;
	private Date createTime;
	private String modifyBy;
	private Date modifyTime;
	private String branches;//缺陷严重程度分类条数
	
	public IssueSeverityPo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IssueSeverityPo(String severityCode, String severityDesc, String moduleState, String createBy,
			Date createTime, String modifyBy, Date modifyTime) {
		super();
		this.severityCode = severityCode;
		this.severityDesc = severityDesc;
		this.moduleState = moduleState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}
	
	
	@Override
	public String toString() {
		return "IssueSeverityPo [severityCode=" + severityCode + ", severityDesc=" + severityDesc + ", moduleState="
				+ moduleState + ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", modifyTime=" + modifyTime + ", branches=" + branches + "]";
	}

	public String getSeverityCode() {
		return severityCode;
	}

	public void setSeverityCode(String severityCode) {
		this.severityCode = severityCode;
	}

	public String getSeverityDesc() {
		return severityDesc;
	}

	public void setSeverityDesc(String severityDesc) {
		this.severityDesc = severityDesc;
	}

	public String getModuleState() {
		return moduleState;
	}

	public void setModuleState(String moduleState) {
		this.moduleState = moduleState;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBranches() {
		return branches;
	}

	public void setBranches(String branches) {
		this.branches = branches;
	}
	
	
}
