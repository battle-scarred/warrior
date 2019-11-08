package com.service.app_category;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.app_category.App_category_Mapper;
import com.entity.App_category;
@Service
public class App_category_Service_Impl implements App_category_Service{
	@Resource
	private App_category_Mapper categoryMapper;
	
	@Override
	public List<App_category> getCategoryList(String parentId) {
		List<App_category> list =null;
		try {
			list = categoryMapper.getCategoryList(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
