package com.huibo.issue.po;
/**
* <p>Title: 缺陷跟踪管理系统 - InfoIssueProjectPO</p>
*
* <p>Description:项目信息实体类po</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
public class InfoIssueProjectPO {

	private String projectId; //project_ID//项目编号
	private String projectName;//project_NAME;//项目名称
	private String projectIntro;//project_INTRO;//项目简介
	private String projectDesc;//project_DESC;//项目描述
	private String projectState;//project_STATE;//当前状态
	private String pProjectId;//p_project_ID;//上级目录
	private String createBy;//create_BY;//创建人
	private String createTime;//create_TIME;//创建时间
	private String modifyBy;//modify_BY;//修改人
	private String modifyTime;//modify_TIME;//修改时间
	/**
	 * 
	 */
	public InfoIssueProjectPO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param projectId
	 * @param projectName
	 * @param projectIntro
	 * @param projectDesc
	 * @param projectState
	 * @param pProjectId
	 * @param createBy
	 * @param createTime
	 * @param modifyBy
	 * @param modifyTime
	 */
	public InfoIssueProjectPO(String projectId, String projectName, String projectIntro, String projectDesc,
			String projectState, String pProjectId, String createBy, String createTime, String modifyBy,
			String modifyTime) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectIntro = projectIntro;
		this.projectDesc = projectDesc;
		this.projectState = projectState;
		this.pProjectId = pProjectId;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectIntro() {
		return projectIntro;
	}
	public void setProjectIntro(String projectIntro) {
		this.projectIntro = projectIntro;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getProjectState() {
		return projectState;
	}
	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}
	public String getpProjectId() {
		return pProjectId;
	}
	public void setpProjectId(String pProjectId) {
		this.pProjectId = pProjectId;
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
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "InfoIssueProjectPO [projectId=" + projectId + ", projectName=" + projectName + ", projectIntro="
				+ projectIntro + ", projectDesc=" + projectDesc + ", projectState=" + projectState + ", pProjectId="
				+ pProjectId + ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", modifyTime=" + modifyTime + "]";
	}
}
