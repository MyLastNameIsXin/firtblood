package com.huibo.issue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.issue.bo.XinDefectListsBo;
import com.huibo.issue.service.StatisticalChartService;
/**
* <p>Title: 缺陷跟踪管理系统 - StatisticalChartController</p>
*
* <p>Description:统计分析页面控制器controller</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 辛宝山
* @version 1.0
*/
@Controller
public class StatisticalChartController {
	@Autowired
	private StatisticalChartService scService;
	
	
	/**
	 * 	缺陷分类占比
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/classify")
	public Map<String, Object> classificationOf(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.classificationOf(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷状态分布
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/distribution")
	public Map<String, Object> distribution(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.distribution(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	缺陷严重程度
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/seriousness")
	public Map<String,Object> seriousness(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.seriousness(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷日报
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/daily")
	public Map<String, Object> daily(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.daily(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷月报
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/montyly")
	public Map<String, Object> montyly(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.montyly(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷月报导出
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/montylyAll")
	public Map<String, Object> montylyAll(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.montylyAll(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 	缺陷日报导出
	 * @param bo 创建开始时间，创建结束时间，项目编号
	 * @return
	 */
	@RequestMapping("/dailyAll")
	public Map<String, Object> dailyAll(XinDefectListsBo bo){
		Map<String, Object> map=new HashMap<>();
		try {
			map=scService.dailyAll(bo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return map;
	}
}
