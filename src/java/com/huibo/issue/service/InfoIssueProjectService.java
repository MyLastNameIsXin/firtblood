package com.huibo.issue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.huibo.issue.bo.InfoIssueProjectBO;
import com.huibo.issue.dao.InfoIssueProjectDao;
import com.huibo.issue.po.InfoIssueProjectPO;
/**
* <p>Title: 缺陷跟踪管理系统 - InfoIssueProjectService</p>
*
* <p>Description:项目管理页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
@Service
public class InfoIssueProjectService {

	@Autowired
	private InfoIssueProjectDao infoIssueProjectDao;
	
	
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public List<InfoIssueProjectBO> findProtectStartInfo(String id) {
		if(id==null || id=="")id="000";
		List<InfoIssueProjectBO> list = infoIssueProjectDao.findProtectStartInfo(id);
		for(InfoIssueProjectBO bo : list) {
			String pid = bo.getProjectId();
			Integer count = 0;
			count =   infoIssueProjectDao.findChildInfo(pid);
			bo.setState(count>0?"closed":"open");
		}
		return list;
	}
	
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	public List<InfoIssueProjectBO> findProtectInfo(String id) {
		if(id==null || id=="")id="000";
		List<InfoIssueProjectBO> list = infoIssueProjectDao.findProtectInfo(id);
		for(InfoIssueProjectBO bo : list) {
			String pid = bo.getProjectId();
			Integer count = 0;
			count =   infoIssueProjectDao.findChildInfo(pid);
			bo.setState(count!=0?"closed":"open");
		}
		return list;
	}

	/**
	 * 新增项目
	 * @param po
	 * @return
	 */
	public Integer addProtectInfo(InfoIssueProjectPO po) {
		String userId = CommonFunction.getUserFromSession().getUserId();
		po.setCreateBy(userId);
		po.setCreateTime(new Date().toLocaleString());
		if(po.getpProjectId()==null || po.getpProjectId().equals("") )
			po.setpProjectId("000");
		Integer  row = infoIssueProjectDao.addProtectInfo(po);
		return row;
	}

	/**
	 * 修改项目
	 * @param po
	 * @return
	 */
	public Integer modifyProtectInfo(InfoIssueProjectPO po) {
		//获取当前修改项目状态
		String pState = po.getProjectState();
		//获取当前修改项目id
		String pid = po.getProjectId();
		Boolean b = true;
		if(pState.equals("0")) {//如果当前项目为无效
			List<InfoIssueProjectBO> list = infoIssueProjectDao.findLikeProtectInfo(pid);
			for(InfoIssueProjectBO bo : list) {
				bo.setProjectState("0");
				infoIssueProjectDao.modifyProtectInfo(bo);
			}
		}
		/*else {
			while(b) {
				InfoIssueProjectBO bo = infoIssueProjectDao.findFatherProtectInfo(pid);
				bo.setProjectState("1");
				infoIssueProjectDao.modifyProtectInfo(bo);
				pid = bo.getpProjectId();
				if(bo.getpProjectId().equals("000")) {
					b=false;
				}
			}
		}*/
		//修改当前项目
		String userId = CommonFunction.getUserFromSession().getUserId();
		po.setModifyBy(userId);
		po.setModifyTime(new Date().toLocaleString());
		Integer  row = infoIssueProjectDao.modifyProtectInfo(po);
		
		return row;
	}

	public InfoIssueProjectBO findFatherProtectInfo(String pid) {
		InfoIssueProjectBO bo = infoIssueProjectDao.findFatherProtectInfo(pid);
		return bo;
	}

	public Integer findProtectId(String id) {
		Integer row = infoIssueProjectDao.findProtectId(id);
		return row;
	}


}
