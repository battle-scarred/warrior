package com.service.app_info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.app_info.App_info_Mapper;
import com.entity.App_info;
import com.entity.App_version;

@Service
public class App_info_Service_Impl implements App_info_Service {
	@Resource
	private App_info_Mapper appInfo;

	@Override
	public List<App_info> getlist(String querySoftwareName, Integer queryStatus, Integer queryFlatformId,
			Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer pageIndex,Integer status) {
		List<App_info> list = null;
		try {
			list = appInfo.getlist(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1,
					queryCategoryLevel2, queryCategoryLevel3, (pageIndex - 1) * 5,status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getcount(String querySoftwareName, Integer queryStatus, Integer queryFlatformId,
			Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3,Integer status) {
		int i = 0;
		try {
			i = appInfo.getcount(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1,
					queryCategoryLevel2, queryCategoryLevel3,status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int update(String id, String versionId) {
		int i = 0;
		try {
			i = appInfo.update(id, versionId);
		} catch (Exception e) {
			throw e;
		}

		return i;
	}

	@Override
	public App_info getappinfo(String id) {
		App_info info = null;
		try {
			info = appInfo.getappinfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public int updateClass(App_info app_info) {
		int i = 0;
		try {
			i = appInfo.updateClass(app_info);
		} catch (Exception e) {
			throw e;
		}
		return i;
	}

	@Override
	public int addappinfo(App_info appinfo) {
		int i = 0;
		try {
			i = this.appInfo.addappinfo(appinfo);
		} catch (Exception e) {
			throw e;
		}
		return i;
	}

	@Override
	public App_info SelectInfo(String id) {
		App_info info = null;
		try {

			info = appInfo.SelectInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	
	@Override
	public int delete(String id) {
		int i = 0;
		try {
			i = appInfo.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public boolean update(App_info appinfo) {
		boolean fa = false;
		switch (appinfo.getStatus()) {
		case 2:
		case 5:
			appinfo.setStatus(4);
			this.appInfo.updateClass(appinfo);
			fa = true;
			break;
		case 4:
			appinfo.setStatus(5);
			fa = true;
			break;
		default:
			fa = false;
		}
		return fa;
	}

}
