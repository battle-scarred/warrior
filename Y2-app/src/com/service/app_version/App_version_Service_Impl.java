package com.service.app_version;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.dao.app_version.App_version_Mapper;
import com.entity.App_version;
@Service
public class App_version_Service_Impl implements App_version_Service{
	@Resource
	private App_version_Mapper versionMapper;
	
	@Override
	public List<App_version> getList(String id) {
		List<App_version> list = null;
		try {
			list = versionMapper.getList(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int add(App_version appversion) {
		int i = 0;
		try {
			i = versionMapper.add(appversion);
		} catch (Exception e) {
			throw e;
		}
		
		return i;
	}
	@Override
	public App_version getClassversion(String appId) {
		App_version app = null;
		try {
			app = versionMapper.getClassversion(appId);
		} catch (Exception e) {
			throw e;
		}
		return app;
	}

	@Override
	public App_version seletcID(String vid) {
		App_version appversion = null;
		try {
			appversion=versionMapper.seletcID(vid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appversion;
	}

	@Override
	public int update(App_version app_version) {
		int i = 0;
		try {
			i = versionMapper.update(app_version);
		} catch (Exception e) {
			throw e;
		}
		return i;
	}
}
