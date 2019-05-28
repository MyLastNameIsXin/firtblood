package com.huibo.issue.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.IIssueAttachBo;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.dao.DefectListsDao;
import com.huibo.issue.po.IIssueAttachPo;
import com.huibo.issue.po.InfoIssueProjectPO;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueStatePo;
/**
* <p>Title: 缺陷跟踪管理系统 - DefectListsService</p>
*
* <p>Description:缺陷列表页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Service
public class DefectListsService {
	@Autowired
	private DefectListsDao dao;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 	缺陷管理页面加载项目名下拉列表
	 * @return
	 */
	public Map<String, Object> queryItemName() {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		List<InfoIssueProjectPO> list=dao.queryItemName(userId);
		map.put("list", list);
		return map;
	}
	/**
	 * 	生成缺陷管理状态下拉列表
	 * @return
	 */
	public Map<String, Object> queryState() {
		Map<String, Object> map=new HashMap<>();
		List<IssueStatePo> list=dao.queryState();
		map.put("list", list);
		return map;
	}
	/**
	 * 	新增修改面板中的项目下拉
	 * @return
	 */
	public Map<String, Object> queryItemData() {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		List<InfoIssueProjectPO> list=dao.queryItemData(userId);
		map.put("list", list);
		return map;
	}
	/**
	 * 	新增面板中缺陷分类下拉
	 * @return
	 */
	public Map<String, Object> itemDefect() {
		Map<String, Object> map=new HashMap<>();
		List<InfoIssueProjectPO> list=dao.itemDefect();
		map.put("list", list);
		return map;
	}
	/**
	 * 	新增修改面板中严重程度下拉
	 * @return
	 */
	public Map<String, Object> itemDegree() {
		Map<String, Object> map=new HashMap<>();
		List<InfoIssueProjectPO> list=dao.itemDegree();
		map.put("list", list);
		return map;
	}
	/**
	 * 	新增修改面板中优先级下拉
	 * @return
	 */
	public Map<String, Object> itemPriority() {
		Map<String, Object> map=new HashMap<>();
		List<InfoIssueProjectPO> list=dao.itemPriority();
		map.put("list", list);
		return map;
	}
	/**
	 * 	新增面板父级项目下拉
	 * @param bo 当前项目的编号
	 * @return
	 */
	public Map<String, Object> itemaParentFlaw(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		List<InfoIssueProjectPO> list=dao.itemaParentFlaw(bo);
		map.put("list", list);
		return map;
	}
	/**
	 * 	新增一条缺陷记录
	 * @param file 上传文件
	 * @param bo 缺陷内容
	 * @return 受影响行数
	 */
	public Map<String, Object> addItemDefect(MultipartFile file, XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setIssueState("01");//加入分配状态，默认为01未分配
		bo.setIssueUseId(userId);
		int row=dao.addItemDefect(bo);
		IssueBaseInfoPo bese=dao.queryMaxId();
		String strFileName=UUID.randomUUID()+"_"+file.getOriginalFilename();
		try {
			file.transferTo(new File("d:/TextUpLoad/"+strFileName));//使用随机数产生上传的文件名字
		} catch (IllegalStateException | IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String strType = file.getContentType();//获取文件类型
		String str = null;
		//判断上传文件类型
		if((strType.toLowerCase()).matches("image(.*)")) {
			str = "1";
		}else {
			str = "0";
		}
		IIssueAttachBo attachBo= new IIssueAttachBo();
		attachBo.setIssueid(bese.getIssueId());
		attachBo.setAttachFile(strFileName);//获取上传图片的名字
		attachBo.setMiniType(strType);
		attachBo.setFileSize(file.getSize());//获取上传文件的大小设置入bo
		attachBo.setIsPic(str);
		attachBo.setUserId(userId);
		int row1=dao.addAttach(attachBo);
		if (row > 0 && row1 >0 ) {//上传成功
			map.put("row", 1);
		}else if (row1 != 1) {//附件上传失败
			map.put("row", -1);
		}else {
			map.put("row", 0);//新增失败
		}
		return map;
	}
	/**
	 * 	用于填充缺陷列表表格
	 * @param bo
	 * @return
	 */
	public Map<String, Object> queryAllData(XinDefectListsBo bo) {
		Page page=new Page(bo);
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		Map<String, Object> map=new HashMap<>();
		List<IssueBaseInfoPo> list=dao.queryAllData(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 	查询一条缺陷信息
	 * @param userID 缺陷编号
	 * @return
	 */
	public Map<String, Object> forDetails(XinDefectListsBo userID) {
		Map<String, Object> map=new HashMap<>();
		IssueBaseInfoPo list=dao.forDetails(userID);
		IIssueAttachPo attachId=dao.queryAttach(list.getIssueId());
		String userId = CommonFunction.getUserFromSession().getUserId();
		list.setIssueAttach(attachId);
		map.put("list", list);
		map.put("userId", userId);//返回当前用户名用于判断当前用户是否可以修改
		return map;
	}
	/**
	 * 	删除一条缺陷信息
	 * @param bo
	 * @return
	 */
	public Map<String, Object> removeIssueById(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		IssueBaseInfoPo baseInfo=dao.forDetails(bo);
		String issueUserId=baseInfo.getCreateBy();
		int row=0;
		int row1=0;
		if (userId.equals(issueUserId)) {
			if (baseInfo.getIssueStateNum().equals("01")) {
				 row =dao.removeIssueState(baseInfo.getIssueId());
				 row1=dao.removeAttach(baseInfo.getIssueId());
				 if (row>0 || row1>0) {
					map.put("row", 1);
					return map;//删除操作成功
				}
			}
			map.put("row", 0);//该条缺陷不可被删除因为状态不是待分配
			return map;
		}
		map.put("row", -1);//该用户没有权限删除该条缺陷记录
		return map;
	}
	/**
	 * 	预览图片和文件下载
	 * @param bo
	 * @return
	 */
	public void showImg(IIssueAttachBo bo,HttpServletResponse response) {
		IIssueAttachPo attachId=dao.queryAttachById(bo.getAttachId());
		File file=new File("d:\\TextUpLoad\\"+attachId.getAttachFile());
		if (file.exists()) {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="+attachId.getAttachFile().split("_")[1]);
			InputStream input=null;
			try {
				input=new FileInputStream(file);
				IOUtils.copy(input, response.getOutputStream());
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				try {
					if (input!=null) {
						input.close();
					}
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 	修改缺陷信息
	 * @param file 附件信息
	 * @param bo	缺陷信息
	 * @return 缺陷修改状态
	 */
	public Map<String, Object> modification(MultipartFile file, XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		int row=-2;
		int row1=0;
		String userId = CommonFunction.getUserFromSession().getUserId();
		bo.setUserId(userId);
		if(file != null) {
			dao.removeAttach(bo.getIssueId());
			//删除原本的附件记录
			if (file.getSize() > 1) {
				//当附件不为空时
				String strFileName=UUID.randomUUID()+file.getOriginalFilename();
				try {
					file.transferTo(new File("d:/TextUpLoad/"+strFileName));//使用随机数产生上传的文件名字
				} catch (IllegalStateException | IOException e) { 
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				String strType = file.getContentType();//获取文件类型
				String str = null;
				//判断上传文件类型
				if((strType.toLowerCase()).matches("image(.*)")) {
					str = "1";
				}else {
					str = "0";
				}
				IIssueAttachBo attachBo= new IIssueAttachBo();
				attachBo.setIssueid(bo.getIssueId());
				attachBo.setAttachFile(strFileName);//获取上传图片的名字
				attachBo.setMiniType(strType);
				attachBo.setFileSize(file.getSize());//获取上传文件的大小设置入bo
				attachBo.setIsPic(str);
				attachBo.setUserId(userId);
				row=dao.addAttach(attachBo);
			}
		}
		 row1=dao.modification(bo);
		 //修改缺陷信息表信息
		if (row !=0 && row1 !=0) {//判断修改的缺陷信息和附件信息是否成功
			map.put("row", 1);
		}else if (row != 1) {
			map.put("row", -1);
		}else {
			map.put("row", 0);
		}
		return map;
	}

}
