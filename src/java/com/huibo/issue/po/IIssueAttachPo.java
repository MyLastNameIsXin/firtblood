package com.huibo.issue.po;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueAttachPo</p>
*
* <p>Description:附件上传实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
import java.util.Date;

public class IIssueAttachPo {
	private Integer attachId;//附件序号
	private String attachDesc;//附件描述
	private Integer	issueid;//缺陷序号
	private String	attachFile;//文件名
	private long	fileSize;//文件大小
	private String	miniType;//文件类型
	private String	isPic;//是否图片
	private String	logState;//当前状态
	private String	createBy;//创建人
	private Date	createTime;//创建时间
	private String	modifyBy;//修改人
	private Date modifyTime;//修改时间
	public IIssueAttachPo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	@Override
	public String toString() {
		return "IIssueAttachPo [attachId=" + attachId + ", attachDesc=" + attachDesc + ", issueid=" + issueid
				+ ", attachFile=" + attachFile + ", fileSize=" + fileSize + ", miniType=" + miniType + ", isPic="
				+ isPic + ", logState=" + logState + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + "]";
	}
	public IIssueAttachPo(Integer attachId, String attachDesc, Integer issueid, String attachFile, long fileSize,
			String miniType, String isPic, String logState, String createBy, Date createTime, String modifyBy,
			Date modifyTime) {
		super();
		this.attachId = attachId;
		this.attachDesc = attachDesc;
		this.issueid = issueid;
		this.attachFile = attachFile;
		this.fileSize = fileSize;
		this.miniType = miniType;
		this.isPic = isPic;
		this.logState = logState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}
	public Integer getAttachId() {
		return attachId;
	}
	public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}
	public String getAttachDesc() {
		return attachDesc;
	}
	public void setAttachDesc(String attachDesc) {
		this.attachDesc = attachDesc;
	}
	public Integer getIssueid() {
		return issueid;
	}
	public void setIssueid(Integer issueid) {
		this.issueid = issueid;
	}
	public String getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getMiniType() {
		return miniType;
	}
	public void setMiniType(String miniType) {
		this.miniType = miniType;
	}
	public String getIsPic() {
		return isPic;
	}
	public void setIsPic(String isPic) {
		this.isPic = isPic;
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
