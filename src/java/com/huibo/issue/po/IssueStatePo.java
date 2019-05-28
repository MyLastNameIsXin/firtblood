package com.huibo.issue.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bn.javax.po.BasePo;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueStatePo</p>
*
* <p>Description:缺陷状态实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueStatePo extends BasePo {
	private String sateCode;
	private String stateDesc;
	private String moduleState;
	private String createBy;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date createTime;
	private String modifyBy;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date modifyTime;
	private String branches;//缺陷分布条数
	public IssueStatePo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public IssueStatePo(String sateCode, String stateDesc, String moduleState, String createBy, Date createTime,
			String modifyBy, Date modifyTime) {
		super();
		this.sateCode = sateCode;
		this.stateDesc = stateDesc;
		this.moduleState = moduleState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "IssueState [sateCode=" + sateCode + ", stateDesc=" + stateDesc + ", moduleState=" + moduleState
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", modifyTime="
				+ modifyTime + "]";
	}
	public String getSateCode() {
		return sateCode;
	}
	public void setSateCode(String sateCode) {
		this.sateCode = sateCode;
	}
	public String getStateDesc() {
		return stateDesc;
	}
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
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
