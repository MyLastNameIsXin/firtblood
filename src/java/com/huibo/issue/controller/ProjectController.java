package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.huibo.issue.bo.ProjectRolesBo;
import com.huibo.issue.service.ProjectService;

/**
* <p>Title: 缺陷跟踪管理系统 - ProjectController</p>
*
* <p>Description:角色信息页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Controller
public class ProjectController {

	@Autowired
	private ProjectService pService;
	/**
	 * 	角色信息查询方法
	 * @return
	 */
	@RequestMapping("/queryAllRole")
	public Map<String, Object> queryAllRole(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try {
			map=pService.queryAll(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	查询一条角色信息用于填充修改面板
	 * @param bo 接受类型
	 * @return
	 */
	@RequestMapping("/queryRole")
	public Map<String, Object> queryRole(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try {
			map=pService.queryRole(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	查询可操作状态
	 * @return
	 */
	@RequestMapping("/operability")
	public Map<String, Object> operability(){
		Map<String, Object> map =new HashMap<String,Object>();
		try {
			map=pService.operability();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	添加角色信息方法
	 * @return
	 */
	@RequestMapping("/addRole")
	public Map<String, Object> addRole(String modRoleCode,@RequestParam("obj[roleName]")String roleName,@RequestParam("obj[sateCode][]") String[] stateCode,@RequestParam("obj[roleCode]")String roleCode){
		Map<String, Object> map=new HashMap<>();
		try {
			map=pService.addRole(roleCode,roleName,stateCode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	删除角色信息
	 * @param roleCode
	 * @return
	 */
	@RequestMapping("/removeRole")
	public Map<String, Object> removeRole(@RequestParam("roleCode[]") String[] roleCode) {
		Map<String, Object> map=new HashMap<>();
		try {
			map=pService.removeRole(roleCode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 启用禁用角色信息
	 * @param roleCode 角色的编号
	 * @param moduleState 角色的状态
	 * @return 受影响行数
	 */
	@RequestMapping("/starFor")
	public Map<String, Object> starFor(String roleCode,String moduleState){
		Map<String, Object> map=new HashMap<>();
		try {
			map=pService.starFor(roleCode,moduleState);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	修改角色信息
	 * @param roleCode 角色编号
	 * @param roleName 角色名称
	 * @param stateCode 角色得可操作状态
	 * @return
	 */
	@RequestMapping("/modifierRole")
	public Map<String, Object> modifierRole(String modRoleCode,@RequestParam("obj[roleName]")String roleName,@RequestParam("obj[sateCode][]") String[] stateCode){
		Map<String, Object> map=new HashMap<>();
		try {
			map=pService.modifierRole(modRoleCode,roleName,stateCode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	验证编号是否已经存在
	 * @param bo
	 * @return
	 */
	@RequestMapping("/erifyRoleCode")
	public Map<String, Object> erifyRoleCode(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<>();
		try {
			map=pService.erifyRoleCode(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	验证角色名称是否已经存在
	 * @param bo
	 * @return
	 */
	@RequestMapping("/erifyRoleName")
	public Map<String, Object> erifyRoleName(ProjectRolesBo bo) {
		Map<String, Object> map=new HashMap<>();
		try {
			map=pService.erifyRoleName(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}

}
