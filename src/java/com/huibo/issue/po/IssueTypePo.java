package com.huibo.issue.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bn.javax.po.BasePo;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueTypePo</p>
*
* <p>Description:缺陷分类实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public class IssueTypePo extends BasePo{
	private String typeCode;
	private String typeDesc;
	private String moduleState;
	private String createBy;
	@DateTimeFormat(pattern= "yyyy-MM-dd HH-mm-ss")
	@JsonFormat(pattern="yyyy-MM-dd HH-mm")
	private Date createTime;
	private String modifyBy;
	@DateTimeFormat(pattern= "yyyy-MM-dd HH-mm-ss")
	@JsonFormat(pattern="yyyy-MM-dd HH-mm")
	private Date modifyTime;
	private String branches;//统计分析的聚合结果
	
	public IssueTypePo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IssueTypePo(String typeCode, String typeDesc, String moduleState, String createBy, Date createTime,
			String modifyBy, Date modifyTime) {
		super();
		this.typeCode = typeCode;
		this.typeDesc = typeDesc;
		this.moduleState = moduleState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
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
