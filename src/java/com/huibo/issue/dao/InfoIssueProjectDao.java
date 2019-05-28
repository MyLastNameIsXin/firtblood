package com.huibo.issue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huibo.issue.bo.InfoIssueProjectBO;
import com.huibo.issue.po.InfoIssueProjectPO;
/**
* <p>Title: 缺陷跟踪管理系统 - InfoIssueProjectDao</p>
*
* <p>Description:项目管理页面持久层接口dao</p>
*
* <p>Copyright: Copyright huiboYC(c) 2019</p>
*
* <p>Company: 重庆汇博人才</p>
*
* @author 黎李
* @version 1.0
*/
public interface InfoIssueProjectDao {

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public List<InfoIssueProjectBO> findProtectInfo(@Param("id") String id);

	public List<InfoIssueProjectBO> findProtectStartInfo(@Param("id") String id);
	
	
	
	public Integer findChildInfo(@Param("pid") String pid);

	public List<InfoIssueProjectBO> findLikeProtectInfo(@Param("pid") String pid);
	
	/**
	 * 新增
	 * @param po
	 * @return
	 */
	public Integer addProtectInfo(InfoIssueProjectPO po);

	/**
	 * 修改
	 * @param po
	 * @return
	 */
	public Integer modifyProtectInfo(InfoIssueProjectPO po);

	/**
	 * 查询父级
	 * @param pid
	 * @return
	 */
	public InfoIssueProjectBO findFatherProtectInfo(@Param("pid") String pid);

	/**
	 * 查询protectID是否已经注册
	 * @param id
	 * @return
	 */
	public Integer findProtectId(String id);

	

}
