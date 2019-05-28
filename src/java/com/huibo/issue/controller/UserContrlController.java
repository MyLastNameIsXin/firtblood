package com.huibo.issue.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.UserContrlBo;
import com.huibo.issue.service.UserContrlService;
/**
* <p>Title: 缺陷跟踪管理系统 - UserContrlController</p>
*
* <p>Description:用户管理页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黄媛媛
* @version 1.0
*/
@Controller
public class UserContrlController {
	@Autowired
	private UserContrlService userContrlService;
	@Autowired
	private HttpServletRequest request;
	
	//搜索全部
	@RequestMapping("/searchAllUser")
	public Map getAllUserContrl(UserContrlBo userContrl){
		Map map=null;				
			try {
				Page page=new Page(userContrl);
				map = this.userContrlService.getAllUserContrl(page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return map;
	}
	//验证userId是否存在
	@RequestMapping("/verifyUserId")
	public Map verifyUserId(UserContrlBo userContrl) {
		Map map=null;				
		try {
			map = this.userContrlService.verifyUserId(userContrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return map;
	}
	
	
	//增加用户
	@RequestMapping("/addUserContrl")
	public Map addUserContrl(UserContrlBo userContrl) {
		Map map=null;
		try {
			map=this.userContrlService.addUserContrl(userContrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	//修改用户
	@RequestMapping("/modifUserContrl")
	public Map modifUserContrl(UserContrlBo userContrl) {
		Map map=null;
		try {
			map=this.userContrlService.modifUserContrl(userContrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	//删除用户信息
	@RequestMapping("/deleteUserContrl")
	public Map deleteUserContrl(@RequestParam("userIds[]") String[] codes){
		Map map=null;
		try {
			map=this.userContrlService.deleteUserContrl(codes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
}
