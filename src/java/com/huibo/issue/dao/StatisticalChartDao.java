package com.huibo.issue.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.po.IssueBaseInfoPo;
import com.huibo.issue.po.IssueSeverityPo;
import com.huibo.issue.po.IssueStatePo;
import com.huibo.issue.po.IssueTypePo;
import com.huibo.issue.po.IssueWorkLogPo;
/**
* <p>Title: 缺陷跟踪管理系统 - StatisticalChartDao</p>
*
* <p>Description:统计分析页面持久层接口dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/

public interface StatisticalChartDao {
	/**
	 * 	查询缺陷分类占比
	 * @param bo
	 * @return
	 */
	public List<IssueTypePo> classificationOf(XinDefectListsBo bo);
	/**
	 * 	缺陷状态分布
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public List<IssueStatePo> distribution(XinDefectListsBo bo);
	/**
	 * 	缺陷严重程度
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public List<IssueSeverityPo> seriousness(XinDefectListsBo bo);
	/**
	 * 	缺陷日报
	 * @param page 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public List<IssueWorkLogPo> daily(Page page);
	/**
	 * 	缺陷月报
	 * @param page 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public List<IssueWorkLogPo> montyly(Page page);
	/**
	 * 	缺陷月报导出
	 * @param page 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public List<IssueWorkLogPo> montylyAll(XinDefectListsBo bo);
	/**
	 * 	缺陷日报导出
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	public List<IssueWorkLogPo> dailyAll(XinDefectListsBo bo);

}
