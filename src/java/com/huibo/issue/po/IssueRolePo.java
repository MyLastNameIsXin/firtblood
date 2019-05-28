package com.huibo.issue.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bn.javax.po.BasePo;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* <p>Title: 缺陷跟踪管理系统 - IssueRolePo</p>
*
* <p>Description:角色信息实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueRolePo extends BasePo {
	private String roleCode;
	private String roleName;
	private String canDo;//可操作状态
	private String moduleState;
	private String createBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	private String modifyBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date modifyTime;

	public String getCanDo() {
		return canDo;
	}

	public void setCanDo(String canDo) {
		this.canDo = canDo;
	}

	public IssueRolePo() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public IssueRolePo(String roleCode, String roleName, String moduleState, String createBy, Date createTime,
			String modifyBy, Date modifyTime) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.moduleState = moduleState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "IssueRole [roleCode=" + roleCode + ", roleName=" + roleName + ", moduleState=" + moduleState
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", modifyTime="
				+ modifyTime + "]";
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
