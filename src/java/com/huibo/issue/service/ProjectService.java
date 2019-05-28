package com.huibo.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.ProjectRolesBo;
import com.huibo.issue.dao.ProjectDao;
import com.huibo.issue.po.IssueRolePo;
import com.huibo.issue.po.IssueStatePo;
/**
* <p>Title: 缺陷跟踪管理系统 - ProjectService</p>
*
* <p>Description:角色信息页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Service
public class ProjectService {

	@Autowired	
	private ProjectDao dao;
	/**
	 * 	查询所有信息
	 * @param bo
	 * @return
	 */
	public Map<String, Object> queryAll(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<>();
		Page page=new Page(bo);
		List<IssueRolePo> list=dao.queryAllRole(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		return map;
	}
	/**
	 * 	查询一条角色信息
	 * @param bo
	 * @return
	 */
	public Map<String, Object> queryRole(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<>();
		List<IssueRolePo> list=dao.queryRole(bo);
		map.put("list", list);
		return map;
	}
	/**
	 * 	查询可操作状态信息
	 * @return
	 */
	public Map<String, Object> operability() {
		Map<String, Object> map=new HashMap<>();
		List<IssueStatePo> list=dao.operability();
		map.put("list", list);
		return map;
	}
	/**
	 * 	添加角色信息
	 * @param bo
	 * @return
	 */
	public Map<String, Object> addRole(String roleCode,String roleName,String[] stateCode) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		int row= dao.addRole(roleCode,roleName,userId);
		int row1=dao.addRele(roleCode,stateCode);
		if (row!=0 && row1!=0) {
			map.put("str", 1);
		}else {
			map.put("str", 0);
		}
		return map;
	}
	/**
	 * 	删除角色信息以及关联信息表
	 * @param roleCode
	 * @return
	 */
	public Map<String, Object> removeRole(String[] roleCode) {
		Map<String, Object> map=new HashMap<>();
		int row=dao.removeRole(roleCode);
		int rows=dao.removeRele(roleCode);
		if (row!=0 && rows!=0) {
			map.put("str", 1);
		}else {
			map.put("str", 0);
		}
		return map;
	}
	/**
	 * 	修改角色得启用禁用状态
	 * @param roleCode 角色得编号
	 * @param moduleState 角色得有效无效状态
	 * @return
	 */
	public Map<String, Object> starFor(String roleCode,String moduleState) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		int row=dao.starFor(roleCode,moduleState,userId);
		if (row!=0) {
			map.put("str", 1);
		}else {
			map.put("str", 0);
		}
		return map;
	}
	/**
	 * 	修改角色得信息
	 * @param roleCode 角色得编号
	 * @param roleName 角色得名称
	 * @param stateCode 角色得可操作状态
	 * @return
	 */
	public Map<String, Object> modifierRole(String roleCode, String roleName, String[] stateCode) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		int row=dao.modifierRole(roleCode,roleName,userId);//修改角色信息
		String[] arr=new String[]{roleCode};
		int row1=dao.removeRele(arr);//删除角色信息关联表中的数据
		int row2=dao.addRele(roleCode, stateCode);//增加角色信息表中数据
		if (row!=0 && row1!=0 && row2!=0) {
			map.put("row", 1);
		}
		return map;
	}
	/**
	 * 	验证用户编号是否已经存在
	 * @param bo
	 * @return
	 */
	public Map<String, Object> erifyRoleCode(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<>();
		if(dao.erifyRoleCode(bo) == 0) {
			map.put("resultCode", "1");
		}else {
			map.put("resultCode", "0");
		}
		return map;
	}
	/**
	 * 	验证新增角色名称是否已经存在
	 * @param roleName
	 * @return
	 */
	public Map<String, Object> erifyRoleName(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<>();
		if (bo.getRoleCode()!=null) {
			List<IssueRolePo> list=dao.queryRole(bo);
			String roleCode=list.get(0).getRoleName();
			if(roleCode.equals(bo.getRoleName())) {
				map.put("resultCode", "1");
			}else if (dao.erifyRoleName(bo) == 0 ) {
				map.put("resultCode", "1");
			} else{
				map.put("resultCode", "0");
			}
		} else {
			if(dao.erifyRoleName(bo) == 0) {
				map.put("resultCode", "1");
			}else {
				map.put("resultCode", "0");
			}
		}
		return map;
	}

	/*public Map<String, Object> erifyRoleName(ProjectRolesBo bo, String logicCode) {
		Map<String, Object> map=new HashMap<>();
		bo.setRoleCode(logicCode);
		List<IssueRolePo> list=dao.queryRole(bo);
		String roleCode=list.get(0).getRoleCode();
		if(dao.erifyRoleName(bo) == 0 && roleCode.equals(logicCode)) {
			map.put("resultCode", "1");
		}else {
			map.put("resultCode", "0");
		}
		return map;
	}*/
	
}
