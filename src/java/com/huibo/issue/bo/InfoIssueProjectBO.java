package com.huibo.issue.bo;

import com.huibo.issue.po.InfoIssueProjectPO;
/**
* <p>Title: 缺陷跟踪管理系统 - InfoIssueProjectBO</p>
*
* <p>Description:项目信息扩展BO</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
public class InfoIssueProjectBO extends InfoIssueProjectPO {

	private String state;

	/**
	 * 
	 */
	public InfoIssueProjectBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param project_ID
	 * @param project_NAME
	 * @param project_INTRO
	 * @param project_DESC
	 * @param project_STATE
	 * @param p_roject_ID
	 * @param create_BY
	 * @param create_TIME
	 * @param modify_BY
	 * @param modify_TIME
	 */
	public InfoIssueProjectBO(String project_ID, String project_NAME, String project_INTRO, String project_DESC,
			String project_STATE, String p_roject_ID, String create_BY, String create_TIME, String modify_BY,
			String modify_TIME) {
		super(project_ID, project_NAME, project_INTRO, project_DESC, project_STATE, p_roject_ID, create_BY, create_TIME,
				modify_BY, modify_TIME);
		// TODO Auto-generated constructor stub
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
