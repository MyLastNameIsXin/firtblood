package com.huibo.issue.bo;

import com.huibo.issue.po.IIssueAttachPo;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueAttachBo</p>
*
* <p>Description:附件上传扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IIssueAttachBo extends IIssueAttachPo {
	private String attachId;//缺陷编号
	private String userId;//用户编号
	
	public String getUserId() {
		return attachId;
	}

	public void setUserId(String userId) {
		this.attachId = attachId;
	}

	
}
