package com.controller.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.App_category;
import com.entity.App_info;
import com.entity.App_version;
import com.entity.Data_dictionary;
import com.entity.PageSupport;
import com.service.app_category.App_category_Service;
import com.service.app_info.App_info_Service;
import com.service.app_version.App_version_Service;
import com.service.data_dictionary.Data_dictionary_Service;

@Controller
@RequestMapping("/backendinfo")
public class Backend_ListController {
	@Resource
	private App_info_Service appinfoService;
	@Resource
	private Data_dictionary_Service dictionaryService;
	@Resource
	private App_category_Service appcategoryService;

	@Resource
	private App_version_Service appversionSevice;

	@RequestMapping("/list")
	public String list(@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) Integer queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex, Model model) {

		if (pageIndex == null || "".equals(pageIndex)) {
			pageIndex = 1;
		}
		// 分页类
		int currview = 5; // 当前页面显示数
		int totalCount = appinfoService.getcount(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1,
				queryCategoryLevel2, queryCategoryLevel3, 1);
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(pageIndex);
		pages.setPageSize(currview);
		pages.setTotalCount(totalCount);
		List<App_category> listcate2 = null;
		List<App_category> listcate3 = null;
		// 二级分类
		if (queryCategoryLevel2 != null) {
			listcate2 = appcategoryService.getCategoryList(queryCategoryLevel1.toString());
		}
		// 三级分类
		if (queryCategoryLevel3 != null) {
			listcate3 = appcategoryService.getCategoryList(queryCategoryLevel2.toString());
		}
		// 平台
		List<Data_dictionary> listdicpt = dictionaryService.getdictionarylist("APP_FLATFORM");
		// 状态
		List<Data_dictionary> listdiczt = dictionaryService.getdictionarylist("APP_STATUS");
		// 一级分类
		List<App_category> listcate = appcategoryService.getCategoryList(null);
		// 查詢显示信息
		List<App_info> list = appinfoService.getlist(querySoftwareName, queryStatus, queryFlatformId,
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, pageIndex, 1);
		// 传数据保存
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryStatus", queryStatus);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);

		model.addAttribute("statusList", listdiczt);
		model.addAttribute("flatFormList", listdicpt);
		model.addAttribute("categoryLevel1List", listcate);
		model.addAttribute("categoryLevel2List", listcate2);
		model.addAttribute("categoryLevel3List", listcate3);
		model.addAttribute("appInfoList", list);
		model.addAttribute("pages", pages);
		return "backend/applist";

	}

	@RequestMapping("/categorylevellist.json")
	@ResponseBody
	public List<App_category> categorylevellist(String pid) {
		System.out.println("=====<<<<<<<<<<<<<<<<<<<===========" + pid);
		if ("".equals(pid))
			pid = null;
		return appcategoryService.getCategoryList(pid);

	}
	// 查看信息

	@RequestMapping("/check")
	public String check(String aid, String vid, Model model,App_info appinfo) {

		appinfo = appinfoService.SelectInfo(aid);
		App_version appver = appversionSevice.seletcID(vid);
		System.out.println("==========,,,,,,,,,,,,,========"+vid);
		model.addAttribute("appInfo", appinfo);
		model.addAttribute("appVersion", appver);

		return "backend/appcheck";

	}
	
	@RequestMapping("/checksave")
	public String update(App_info appinfo){
		if(appinfoService.updateClass(appinfo)>0){
			return "redirect:/backendinfo/list";
		}
		return "backend/appcheck";
		
	}
}
