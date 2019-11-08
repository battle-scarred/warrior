package com.service.app_category;

import java.util.List;

import com.entity.App_category;

public interface App_category_Service {
	/**
	 * 查询所有的分类
	 * @return
	 */
	public List<App_category> getCategoryList(String parentId);
}
