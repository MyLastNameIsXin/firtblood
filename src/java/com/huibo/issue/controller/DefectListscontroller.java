package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.huibo.issue.bo.IIssueAttachBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.service.DefectListsService;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectListscontroller</p>
*
* <p>Description:缺陷列表页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Controller
public class DefectListscontroller {
	@Autowired
	private DefectListsService dService;
	/**
	 * 	缺陷管理页面加载项目名下来列表
	 * @return
	 */
	@RequestMapping("/itempull")
	public Map<String, Object> queryItemName(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.queryItemName();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	生成状态下拉列表
	 * @return
	 */
	@RequestMapping("/statePull")
	public Map<String, Object> statePull(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.queryState();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	新增修改面板中的项目下拉
	 * @return
	 */
	@RequestMapping("/queryItemData")
	public Map<String, Object> queryItemData(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.queryItemData();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	新增面板中缺陷分类下拉
	 * @return
	 */
	@RequestMapping("/itemDefect")
	public Map<String, Object> itemDefect(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.itemDefect();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	新增面板中严重程度下拉
	 * @return
	 */
	@RequestMapping("/itemDegree")
	public Map<String, Object> itemDegree(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.itemDegree();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	新增面板中优先级下拉
	 * @return
	 */
	@RequestMapping("/itemPriority")
	public Map<String, Object> itemPriority(){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.itemPriority();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	新增面板父级缺陷下拉列表
	 * @param bo 当前项目的编号
	 * @return
	 */
	@RequestMapping("/itemaParentFlaw")
	public Map<String, Object> itemaParentFlaw(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.itemaParentFlaw(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	新增缺陷
	 * @param mft 上传附件
	 * @param bo 新增缺陷记录
	 * @return
	 */
	@RequestMapping("/addItemDefect")
	public Map<String, Object> addItemDefect(MultipartFile file,XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.addItemDefect(file,bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	查询所有项目信息
	 * @return
	 */
	@RequestMapping("/queryAllData")
	public Map<String, Object> queryAllData(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.queryAllData(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	查询一条缺陷信息
	 * @return
	 */
	@RequestMapping("/forDetails")
	public Map<String, Object> forDetails(XinDefectListsBo userID){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.forDetails(userID);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	删除一条缺陷信息
	 * @return
	 */
	@RequestMapping("/removeIssueById")
	public Map<String, Object> removeIssueById(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.removeIssueById(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	下载文件
	 * @return
	 */
	@RequestMapping("/showImg")
	public void showImg(IIssueAttachBo bo,HttpServletResponse response){
		try {
			dService.showImg(bo,response);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 	修改缺陷信息
	 * @param file 上传的附件
	 * @param bo	修改的缺陷信息
	 * @return
	 */
	@RequestMapping("/modification")
	public Map<String, Object> modification(MultipartFile file,XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=dService.modification(file,bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
}

