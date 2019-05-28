package com.huibo.issue.bo;

import com.huibo.issue.po.IssueRolePo;
/**
* <p>Title: 缺陷跟踪管理系统 - ProjectRolesBo</p>
*
* <p>Description:角色信息扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class ProjectRolesBo extends IssueRolePo {
	private String[] sateCode;

	public String[] getSateCode() {
		return sateCode;
	}

	public void setSateCode(String[] sateCode) {
		this.sateCode = sateCode;
	}

}
