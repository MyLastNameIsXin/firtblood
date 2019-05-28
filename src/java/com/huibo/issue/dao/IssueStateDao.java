package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IssueStateBo;
/**
* <p>Title: 缺陷跟踪管理系统 - IssueStateDao</p>
*
* <p>Description:缺陷状态页面持久层接口dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 曾超文
* @version 1.0
*/
public interface IssueStateDao {

	List queryIssueState(Page page);

	int addOneState(IssueStateBo bo);

	int deleteStateByCode(String[] codes);

	int modifyStateByCode(IssueStateBo bo, String preCode);

	int modifyStateModulestateByCode(IssueStateBo bo);

	int checkedInfo(String id);
}
