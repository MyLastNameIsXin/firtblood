package com.huibo.issue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.dao.StatisticalChartDao;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueSeverityPo;
import com.huibo.issue.po.IssueStatePo;
import com.huibo.issue.po.IssueTypePo;
import com.huibo.issue.po.IssueWorkLogPo;
/**
* <p>Title: 缺陷跟踪管理系统 - StatisticalChartService</p>
*
* <p>Description:统计分析页面业务层service</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/

@Service
public class StatisticalChartService {
	@Autowired
	private StatisticalChartDao dao;
	/**
	 * 	查询项目缺陷分类以及条数
	 * @param bo 开始时间，结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> classificationOf(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		List<IssueTypePo> list=dao.classificationOf(bo);
		map.put("list", list);
		return map;
	}
	/**
	 * 	缺陷状态分布
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> distribution(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		List<IssueStatePo> list=dao.distribution(bo);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 	缺陷严重程度
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> seriousness(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		List<IssueSeverityPo> list=dao.seriousness(bo);
		map.put("list", list);
		return map;
	}
	/**
	 * 	缺陷日报
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> daily(XinDefectListsBo bo) {
		Page page = new Page(bo);
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		List<IssueWorkLogPo> list=dao.daily(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 	缺陷月报
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> montyly(XinDefectListsBo bo) {
		Page page = new Page(bo);
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+"-31 23:59:59");
		}
		List<IssueWorkLogPo> list=dao.montyly(page);
		map.put("rows",list);
		map.put("total",page.getTotalRecord());
		return map;
	}
	/**
	 * 	缺陷月报导出
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> montylyAll(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+"-31 23:59:59");
		}
		List<IssueWorkLogPo> list=dao.montylyAll(bo);
		map.put("rows",list);
		return map;
	}
	/**
	 * 	缺陷日报导出
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public Map<String, Object> dailyAll(XinDefectListsBo bo) {
		Map<String, Object> map=new HashMap<>();
		if (!bo.getEndTime().isEmpty()) {
			bo.setEndTime(bo.getEndTime()+" 23:59:59");
		}
		List<IssueWorkLogPo> list=dao.dailyAll(bo);
		map.put("rows",list);
		return map;
	}
}
