package com.controller.developer;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.App_category;
import com.entity.App_info;
import com.entity.App_version;
import com.entity.Data_dictionary;
import com.entity.Dev_user;
import com.entity.PageSupport;
import com.service.app_category.App_category_Service;
import com.service.app_info.App_info_Service;
import com.service.app_version.App_version_Service;
import com.service.data_dictionary.Data_dictionary_Service;

@Controller
@RequestMapping("/info")
public class Dev_ListController {
	private static final int String = 0;
	@Resource
	private App_info_Service appinfoService;
	@Resource
	private Data_dictionary_Service dictionaryService;
	@Resource
	private App_category_Service appcategoryService;

	@Resource
	private App_version_Service appversionSevice;

	@RequestMapping("/list")
	public String getListinfo(@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
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
				queryCategoryLevel2, queryCategoryLevel3,null);
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
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, pageIndex,null);
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
		return "developer/appinfolist";
	}

	// 分类查询
	@RequestMapping("/categorylevellist.json")
	@ResponseBody
	public List<App_category> categorylevellist(String pid) {
		System.out.println("=====<<<<<<<<<<<<<<<<<<<===========" + pid);
		if ("".equals(pid))
			pid = null;
		return appcategoryService.getCategoryList(pid);

	}

	// 添加
	@RequestMapping("/appversionadd")
	public String add(Model model, String id, App_version version) {

		List<App_version> appverson = appversionSevice.getList(id);
		model.addAttribute("version", version);
		model.addAttribute("appVersionList", appverson);
		return "developer/appversionadd";
	}

	@RequestMapping(value = "/addversionsave", method = RequestMethod.POST)
	public String addversion(App_version version, String appId, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "a_downloadLink", required = false) MultipartFile[] attachs) {
		////////////
		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean fiag = true;
		String oldFileName = null;
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");

		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];
			if (!attach.isEmpty()) {
				if (i == 0) {
					errorInfo = "a_downloadLink";

				} else if (i == 1) {
					errorInfo = "updaloadWpError";
				}
				oldFileName = attach.getOriginalFilename();// 原文件名
				String prefix = FilenameUtils.getExtension(oldFileName);// 获得原文件后缀
				int fileSize = 500000; // 设置最大的传输。。。。单位B
				if (attach.getSize() > fileSize) {
					request.setAttribute(errorInfo, "上传不能超过500kb");
					fiag = false;
				} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
					String fileName = System.currentTimeMillis() + RandomUtils.nextInt(10000000) + "_Personal.jpg";
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try {
						attach.transferTo(targetFile);

					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute(errorInfo, "上传失败");
						fiag = false;

					}
					if (i == 0) {
						idPicPath = path + File.separator + fileName;
					} else if (i == 1) {

						workPicPath = path + File.separator + fileName;

					}
				} else {
					request.setAttribute(errorInfo, "上传图片格式不正确");
					fiag = false;
				}

			}
		}

		/////////////////////////////////////////// AppInfoSystem/statics/uploadfiles/com.doodleapps
		if (fiag) {
			version.setApkLocPath(idPicPath);
			version.setApkFileName(oldFileName);
			version.setDownloadLink("/AppInfoSystem/statics/uploadfiles/com.doodleapps/" + oldFileName);
			version.setCreatedBy(((App_version) session.getAttribute("devUserSession")).getId());
			version.setCreationDate(new Date());
			int i = appversionSevice.add(version);
			if (i > 0) {
				int h = appinfoService.update(appId, appversionSevice.getClassversion(appId).getId() + "");
				if (h > 0)
					return "";
				return "";
			}
		}

		return "";

	}

	// 跳入修改
	@RequestMapping("/appinfomodify")
	public String modify(App_info appinfo, String id, Model model) {

		appinfo = appinfoService.getappinfo(id);
		model.addAttribute("appInfo", appinfo);
		return "developer/appinfomodify";

	}

	// 动态加载所属平台
	@RequestMapping("/datadictionarylist.json")
	@ResponseBody
	public Object datadictionarylist(String tcode) {

		List<Data_dictionary> list = dictionaryService.getdictionarylist(tcode);
		return list;

	}

	// 修改app信息
	@RequestMapping("/appinfomodifysave")
	public String appinfomodifysave(App_info app_info) {
		int i = appinfoService.updateClass(app_info);
		if (i > 0) {
			return "";
		}
		return "";
	}

	//
	@RequestMapping("/appversionmodify")
	public String appversionmodify(App_version appversion, String vid, String aid, Model model) {

		List<App_version> list = appversionSevice.getList(aid);
		System.out.println(vid + "===============id");

		appversion = appversionSevice.seletcID(vid);
		System.out.println(appversion.getId() + "============");
		model.addAttribute("appVersion", appversion);
		model.addAttribute("appVersionList", list);
		return "developer/appversionmodify";

	}

	// 修改页面保存信息执行修改
	@RequestMapping("/appversionmodifysave")
	public String appversionmodifysave(App_version appversion) {

		int i = appversionSevice.update(appversion);
		if (i > 0) {
			return "developer/appinfolist";
		}
		return "developer/appversionmodify";

	}

	@RequestMapping("/dev/flatform/app/appinfoadd")
	public String appinfoadd(App_info appinfo, Model model) {
		model.addAttribute("appinfo", appinfo);
		return "developer/appinfoadd";

	}

	@RequestMapping("/appinfoaddsave")
	public String addappinfo(App_info appinfo, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach) {
		// 处理数据
		// 处理文件
		String a_logoPicPath = null;
		String path = null;
		if (!attach.isEmpty()) {
			path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "logo");
			String oldFileName = attach.getOriginalFilename(); // 获得原文件名称
			String prefix = FilenameUtils.getExtension(oldFileName); // 获得文件的后缀
			int filesize = 500000; // 限定大小
			if (attach.getSize() > filesize) {
				request.setAttribute("", "*上传文件不大于500KB");
				return "developer/appinfoadd";
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_cx.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();

					// 保存文件
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("e", "*上传文件失败");
						return "developer/appinfoadd";
					}
				} else {
					request.setAttribute("e", "*上传文件图片不合格");
					return "developer/appinfoadd";
				}
			}
		}

		appinfo.setDevId(((Dev_user) session.getAttribute("devUserSession")).getId());
		appinfo.setCreatedBy(((Dev_user) session.getAttribute("devUserSession")).getId());
		appinfo.setCreationDate(new Date());
		appinfo.setLogoLocPath(path);
		appinfo.setLogoPicPath(a_logoPicPath);
		// 添加
		int i = appinfoService.addappinfo(appinfo);
		if (i > 0) {
			return "redirect:/info/list"; // 转发到处理器
		}
		return "developer/appinfoadd";

	}

	// 查看信息
	@RequestMapping("/appview/{appinfoid}")
	public String appview(@PathVariable String appinfoid, Model model) {
		App_info appinfo = appinfoService.SelectInfo(appinfoid);
		List<App_version> list = appversionSevice.getList(appinfoid);
		model.addAttribute("appInfo", appinfo);
		model.addAttribute("appVersionList", list);
		return "developer/appinfoview";

	}

	@RequestMapping("/delapp.json")
	@ResponseBody
	public Object delapp(String id) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			if (appinfoService.delete(id) > 0) {
				map.put("delResult", "true");

			} else {
				map.put("delResult", "false");
			}
		} catch (Exception e) {
			map.put("delResult", "notexist");
			e.printStackTrace();
		}

		return id;

	}

	@RequestMapping(value="/{appId}/sale.json",method = RequestMethod.PUT)
	@ResponseBody
	public Object sale(@PathVariable String appId, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		try {

			App_info appinfo = appinfoService.SelectInfo(appId);

			map.put("errorCode", "0");
			if (appinfo != null) {
				appinfo.setModifyBy(((Dev_user) session.getAttribute("devUserSession")).getId());
				appinfo.setModifyDate(new Date());
				boolean dc = appinfoService.update(appinfo);
				if(dc){
					map.put("resultMsg", "success");
				}else
				{
					map.put("resultMsg", "failed");	
				}
			} else {
				map.put("errorCode", "param000001");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorCode", "exception000001");
		}
		return map;
	}
}
