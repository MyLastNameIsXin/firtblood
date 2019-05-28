package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueTypeBo;

/**
* <p>Title: 缺陷跟踪管理系统 - IssueTypeDao</p>
*
* <p>Description:缺陷分类页面持久层dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public interface IssueTypeDao {

	List queryIssueType(Page page);

	int addOneType(IssueTypeBo bo);

	int deleteTypeByCode(String[] codes);

	int modifyTypeByCode(IssueTypeBo bo, String preCode);

	int modifyTypeModulestateByCode(IssueTypeBo bo);
	
	int checkedInfo(String id);
}
