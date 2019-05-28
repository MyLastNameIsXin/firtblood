package com.huibo.issue.bo;

import com.huibo.issue.po.IssueStatePo;
/**
* <p>Title: 缺陷跟踪管理系统 - XinIssueStateBO</p>
*
* <p>Description:缺陷状态扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class XinIssueStateBO extends IssueStatePo{
	private String stateCode;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	
}
