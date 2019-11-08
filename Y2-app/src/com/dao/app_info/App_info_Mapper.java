package com.dao.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.App_info;

public interface App_info_Mapper {
	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public List<App_info> getlist(@Param("querySoftwareName") String querySoftwareName,
			@Param("queryStatus") Integer queryStatus, 
			@Param("queryFlatformId") Integer queryFlatformId,
			@Param("queryCategoryLevel1") Integer queryCategoryLevel1,
			@Param("queryCategoryLevel2") Integer queryCategoryLevel2,
			@Param("queryCategoryLevel3") Integer queryCategoryLevel3, 
			@Param("pageIndex") Integer pageIndex,@Param("status")Integer status);
	
	/**
	 * 查询中记录数
	 * @param querySoftwareName
	 * @param queryStatus
	 * @param queryFlatformId
	 * @param queryCategoryLevel1
	 * @param queryCategoryLevel2
	 * @param queryCategoryLevel3
	 * @return
	 */
	public int getcount(@Param("querySoftwareName") String querySoftwareName,
			@Param("queryStatus") Integer queryStatus, 
			@Param("queryFlatformId") Integer queryFlatformId,
			@Param("queryCategoryLevel1") Integer queryCategoryLevel1,
			@Param("queryCategoryLevel2") Integer queryCategoryLevel2,
			@Param("queryCategoryLevel3") Integer queryCategoryLevel3,@Param("status")Integer status);
	
	
	/**
	 * 修改
	 * @param id
	 * @param versionId
	 * @return
	 */
	public int update(@Param("id")String id,@Param("versionId")String versionId);
	
	/**
	 * 查询一条信息
	 * @param id
	 * @return
	 */
	public App_info getappinfo(@Param("id")String id);
	
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
	
	
	
}
