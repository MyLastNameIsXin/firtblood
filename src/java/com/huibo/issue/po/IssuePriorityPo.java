package com.huibo.issue.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bn.javax.po.BasePo;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
* <p>Title: 缺陷跟踪管理系统 - IssuePriorityPo</p>
*
* <p>Description:优先级实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public class IssuePriorityPo  extends BasePo{
	private String priorityCode;
	private String priorityDesc;
	private String moduleState;
	private String createBy;
	@DateTimeFormat(pattern= "yyyy-MM-dd HH-mm-ss")
	@JsonFormat(pattern="yyyy-MM-dd HH-mm")
	private Date createTime;
	private String modifyBy;
	@DateTimeFormat(pattern= "yyyy-MM-dd HH-mm-ss")
	@JsonFormat(pattern="yyyy-MM-dd HH-mm")
	private Date modifyTime;
	
	public IssuePriorityPo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IssuePriorityPo(String priorityCode, String priorityDesc, String moduleState, String createBy,
			Date createTime, String modifyBy, Date modifyTime) {
		super();
		this.priorityCode = priorityCode;
		this.priorityDesc = priorityDesc;
		this.moduleState = moduleState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}

	public String getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}

	public String getPriorityDesc() {
		return priorityDesc;
	}

	public void setPriorityDesc(String priorityDesc) {
		this.priorityDesc = priorityDesc;
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

	

}
