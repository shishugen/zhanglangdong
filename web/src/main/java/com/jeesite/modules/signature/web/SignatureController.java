package com.jeesite.modules.signature.web;

import com.alibaba.fastjson.JSONValidator;
import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.portal.entity.PortalBanner;
import com.jeesite.modules.portal.entity.PortalBannerContent;
import com.jeesite.modules.portal.service.PortalBannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页图片内容Controller
 * @author ssg
 * @version 2022-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/signature/")
public class SignatureController extends BaseController {

	@Autowired
	private PortalBannerService portalBannerService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PortalBanner get(String id, boolean isNewRecord) {
		return portalBannerService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(PortalBanner portalBanner, Model model) {
		model.addAttribute("portalBanner", portalBanner);
		return "modules/signature/index";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PortalBanner> listData(PortalBanner portalBanner, HttpServletRequest request, HttpServletResponse response) {
		portalBanner.setPage(new Page<>(request, response));
		Page<PortalBanner> page = portalBannerService.findPage(portalBanner);
		return page;
	}
	
	/**
	 * 查询子表数据
	 */
	@RequestMapping(value = "portalBannerContentListData")
	@ResponseBody
	public Page<PortalBannerContent> subListData(PortalBannerContent portalBannerContent, HttpServletRequest request, HttpServletResponse response) {
		portalBannerContent.setPage(new Page<>(request, response));
		Page<PortalBannerContent> page = portalBannerService.findSubPage(portalBannerContent);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("portal:portalBanner:view")
	@RequestMapping(value = "form")
	public String form(PortalBanner portalBanner, Model model) {
		model.addAttribute("portalBanner", portalBanner);
		return "modules/portal/portalBannerForm";
	}





	
	/**
	 * 列表选择对话框
	 */
	@RequiresPermissions("portal:portalBanner:view")
	@RequestMapping(value = "portalBannerSelect")
	public String portalBannerSelect(PortalBanner portalBanner, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (selectDataJson != null && JSONValidator.from(selectDataJson).validate()){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("portalBanner", portalBanner);
		return "modules/portal/portalBannerSelect";
	}
	
}