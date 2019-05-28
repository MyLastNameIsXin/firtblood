package com.huibo.issue.dao;

import java.util.Date;
import java.util.List;

import com.bn.javax.dao.Page;
/**
* <p>Title: 缺陷跟踪管理系统 - IssuePriorityDao</p>
*
* <p>Description:优先级页面持久层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public interface IssuePriorityDao {

	public List queryIssuePriority(Page page);

	public int modifyIssuePriorityByCode(String prePriorityCode, String priorityCode, String priorityDesc,String userId, Date date);

	public int deleteIssuePriorityByCodes(String[] codes);

	public int modifyIssueModuleStateByCode(String priorityCode, String moduleState);

	public int addOnePriority(String priorityCode, String priorityDesc, String userId, Date date);
	
	int checkedInfo(String id);
}
