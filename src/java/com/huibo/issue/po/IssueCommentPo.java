package com.huibo.issue.po;

import com.bn.javax.po.BasePo;

/**
* <p>Title: 缺陷跟踪管理系统 - IssueCommentPo</p>
*
* <p>Description:缺陷反馈实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
public class IssueCommentPo extends BasePo {
	private Integer	logId;//反馈的记录序号
	private Integer issueId;//缺陷序号
	private String	comment;//评论内容
	private String logState;//当前状态
	private String createBy;//创建人
	private String	createTime;//创建时间
	private String	modifyBy;//修改人
	private String	moifyTime;//修改时间
	private Integer retroactionNum;//反馈条数
	
	public IssueCommentPo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public IssueCommentPo(Integer logId, Integer issueId, String comment, String logState, String createBy,
			String createTime, String modifyBy, String moifyTime) {
		super();
		this.logId = logId;
		this.issueId = issueId;
		this.comment = comment;
		this.logState = logState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.moifyTime = moifyTime;
	}
	@Override
	public String toString() {
		return "IssueCommentPo [logId=" + logId + ", issueId=" + issueId + ", comment=" + comment + ", logState="
				+ logState + ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", moifyTime=" + moifyTime + "]";
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public Integer getRetroactionNum() {
		return retroactionNum;
	}
	public void setRetroactionNum(Integer retroactionNum) {
		this.retroactionNum = retroactionNum;
	}
	
	
}
