package com.service.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.App_version;

public interface App_version_Service {
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public List<App_version> getList(String id);
	
	/**
	 * 添加数据版本
	 * @param appversion
	 * @return
	 */
	public int add(App_version appversion);
	
	/**
	 * 查询最新版本
	 * @param appId
	 * @return
	 */
	public App_version getClassversion(String appId);
	
	/**
	 * 根据
	 * @param id
	 * @return
	 */ 
	public App_version seletcID(String vid);
	
	/**
	 * 修改版本
	 */
	public int update(App_version app_version);
}
