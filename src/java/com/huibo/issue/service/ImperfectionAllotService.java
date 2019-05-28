package com.huibo.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.dao.ImperfectionAllotDao;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.UserContrlPo;
/**
* <p>Title: 缺陷跟踪管理系统 - ImperfectionAllotService</p>
*
* <p>Description:缺陷分配页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Service
public class ImperfectionAllotService {
	@Autowired
	private ImperfectionAllotDao dao;
	/**
	 * 	查询当前管理员可见的所有项目
	 * @param bo 
	 * @return
	 */
	public Map<String, Object> allotIsAdministrator(XinDefectListsBo bo) {
		Page page = new Page(bo);
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		List<IssueBaseInfoPo> list=dao.allotIsAdministrator(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 	加载缺陷分配页面的项目名下拉
	 * @return
	 */
	public Map<String, Object> itemAllotPull() {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		List<InfoIssueProjectPO> list=dao.itemAllotPull(userId);
		map.put("list", list);
		return map;
	}
	/**
	 * 	当前缺陷的详细信息
	 * @param bo 缺陷的编号
	 * @return 缺陷详细信息
	 */
	public Map<String, Object> distribute(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		IssueBaseInfoPo baseInfo=dao.distribute(bo);
		map.put("list", baseInfo);
		return map;
	}
	/**
	 * 	生成分配面板下拉选项
	 * @param bo 项目编号
	 * @return 参与项目的员工姓名和编号
	 */
	public Map<String, Object> designate(XinDefectListsBo bo) {
		Map<String, Object> map = new HashMap<>();
		List<UserContrlPo> userList=dao.queryUserName(bo);
		userList.forEach(System.out::println);
		map.put("list", userList);
		return map;
	}
	/**
	 * 	将缺陷指派出去
	 * @param bo 被指派的缺陷编号以及被指派员工编号以及指派员工编号
	 * @return 是否指派成功
	 */
	public Map<String, Object> assignedTo(XinDefectListsBo bo) {
		Map<String, Object> map = new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		int row=dao.assignedTo(bo);
		map.put("row", row);
		return map;
	}
	
}
