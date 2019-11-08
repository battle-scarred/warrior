package com.service.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.App_info;
import com.entity.App_version;



public interface App_info_Service {
	/**
	 * 查询全部
	 * @return
	 */
	public List<App_info> getlist(String querySoftwareName,
			 Integer queryStatus, 
			 Integer queryFlatformId,
			 Integer queryCategoryLevel1,
			 Integer queryCategoryLevel2,
			 Integer queryCategoryLevel3, 
			 Integer pageIndex,Integer status);
	
	/**
	 * 查询总数
	 * @param querySoftwareName
	 * @param queryStatus
	 * @param queryFlatformId
	 * @param queryCategoryLevel1
	 * @param queryCategoryLevel2
	 * @param queryCategoryLevel3
	 * @return
	 */
	public int getcount(String querySoftwareName,
			 Integer queryStatus, 
			 Integer queryFlatformId,
			 Integer queryCategoryLevel1,
			 Integer queryCategoryLevel2,
			 Integer queryCategoryLevel3,Integer status);
	
	
	/**
	 * 修改
	 * @param id
	 * @param versionId
	 * @return
	 */
	public int update(String id,String versionId);
	
	
	/**
	 * 查询一条信息
	 * @param id
	 * @return
	 */
	public App_info getappinfo(String id);
	
	
	/**
	 * 修改一条数据
	 * @param app_info
	 * @return
	 */
	public int updateClass(App_info app_info);
	
	/**
	 * 添加一条app信息
	 * @param appinfo
	 * @return
	 */
	public int addappinfo(App_info appinfo);

	
	/**
	 * 查询一条信息
	 * @param id
	 * @return
	 */
	public App_info SelectInfo(@Param("id")String id);
	
	
	/**
	 * 删除一条数据
	 * @param id
	 * @return
	 */
	public int delete (@Param("id")String id);
	
	/**
	 * 修改调用
	 * @param appinfo
	 * @return
	 */
	public boolean update(App_info appinfo);
}
