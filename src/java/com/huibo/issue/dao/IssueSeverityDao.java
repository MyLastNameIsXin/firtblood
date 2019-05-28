package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueSeverityBo;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueSeverityDao</p>
*
* <p>Description:严重程度页面持久层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public interface IssueSeverityDao {

	List queryIssueServerity(Page page);

	int addOneServerity(IssueSeverityBo bo);

	int deleteServerityByCode(String[] codes);

	int modifyServerityByCode(IssueSeverityBo bo, String preCode);

	int modifyServerityModulestateByCode(IssueSeverityBo bo);
	
	int checkedInfo(String id);
}
