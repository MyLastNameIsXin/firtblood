package com.huibo.issue.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.synth.SynthScrollPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huibo.issue.bo.IIssueRoleBO;
import com.huibo.issue.dao.IIssueRoleDao;
import com.huibo.issue.po.IIssueRolePO;
/**
* <p>Title: 缺陷跟踪管理系统 - IIssueRoleService</p>
*
* <p>Description:角色分配业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
@Service
public class IIssueRoleService {

	@Autowired
	private IIssueRoleDao iIssueRoleDao;
	
	public List<IIssueRoleBO> getDate(String pid) {
		List<IIssueRoleBO> list = iIssueRoleDao.getDate(pid);
		Set<String> set = new HashSet<String>();
		for(IIssueRoleBO bo:list) {
			set.add(bo.getUserId());
		}
		List<IIssueRoleBO> bos = new ArrayList<IIssueRoleBO>();
		for(String uid:set) {
			
			List<IIssueRoleBO> boList = iIssueRoleDao.getDateByRoleId(pid,uid);
			IIssueRoleBO newBo = boList.get(0);
			String roleName = "";
			for(IIssueRoleBO bol:boList) {
				roleName += bol.getRoleName()+",";
			}
			newBo.setRoleName(roleName.substring(0, roleName.length()-1));
			bos.add(newBo);
		}
		return bos;
	}

	public Integer addRoleInfo(IIssueRolePO po) {
		Integer row = iIssueRoleDao.addRoleInfo(po);
		return row;
	}

	public Integer removeRoleInfo(IIssueRolePO po) {
		Integer row = iIssueRoleDao.removeRoleInfo(po);
		return row;
	}

	public List<IIssueRoleBO> findUserName() {
		List<IIssueRoleBO> list = iIssueRoleDao.findUserName();
		return list;
	}

	public List<IIssueRoleBO> findRoleName() {
		List<IIssueRoleBO> list = iIssueRoleDao.findRoleName();
		return list;
	}

}
