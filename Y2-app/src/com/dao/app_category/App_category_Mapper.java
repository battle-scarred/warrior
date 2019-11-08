package com.dao.app_category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.App_category;

public interface App_category_Mapper {
	/**
	 * 查询所有的分类
	 * @return
	 */
	public List<App_category> getCategoryList(@Param("parentId") String parentId);
}
