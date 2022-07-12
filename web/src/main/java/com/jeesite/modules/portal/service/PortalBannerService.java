package com.jeesite.modules.portal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.portal.entity.PortalBanner;
import com.jeesite.modules.portal.dao.PortalBannerDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.portal.entity.PortalBannerContent;
import com.jeesite.modules.portal.dao.PortalBannerContentDao;

/**
 * 首页图片内容Service
 * @author ssg
 * @version 2022-07-12
 */
@Service
@Transactional(readOnly=true)
public class PortalBannerService extends CrudService<PortalBannerDao, PortalBanner> {
	
	@Autowired
	private PortalBannerContentDao portalBannerContentDao;
	
	/**
	 * 获取单条数据
	 * @param portalBanner
	 * @return
	 */
	@Override
	public PortalBanner get(PortalBanner portalBanner) {
		PortalBanner entity = super.get(portalBanner);
		if (entity != null){
			PortalBannerContent portalBannerContent = new PortalBannerContent(entity);
			portalBannerContent.setStatus(PortalBannerContent.STATUS_NORMAL);
			entity.setPortalBannerContentList(portalBannerContentDao.findList(portalBannerContent));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param portalBanner 查询条件
	 * @param portalBanner.page 分页对象
	 * @return
	 */
	@Override
	public Page<PortalBanner> findPage(PortalBanner portalBanner) {
		return super.findPage(portalBanner);
	}
	
	/**
	 * 查询列表数据
	 * @param portalBanner
	 * @return
	 */
	@Override
	public List<PortalBanner> findList(PortalBanner portalBanner) {
		return super.findList(portalBanner);
	}
	
	/**
	 * 查询子表分页数据
	 * @param portalBannerContent
	 * @param portalBannerContent.page 分页对象
	 * @return
	 */
	public Page<PortalBannerContent> findSubPage(PortalBannerContent portalBannerContent) {
		Page<PortalBannerContent> page = portalBannerContent.getPage();
		page.setList(portalBannerContentDao.findList(portalBannerContent));
		return page;
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param portalBanner
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PortalBanner portalBanner) {
		super.save(portalBanner);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(portalBanner, portalBanner.getId(), "portalBanner_image");
		// 保存 PortalBanner子表
		for (PortalBannerContent portalBannerContent : portalBanner.getPortalBannerContentList()){
			if (!PortalBannerContent.STATUS_DELETE.equals(portalBannerContent.getStatus())){
				portalBannerContent.setBannerId(portalBanner);
				if (portalBannerContent.getIsNewRecord()){
					portalBannerContentDao.insert(portalBannerContent);
				}else{
					portalBannerContentDao.update(portalBannerContent);
				}
			}else{
				portalBannerContentDao.delete(portalBannerContent);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param portalBanner
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PortalBanner portalBanner) {
		super.updateStatus(portalBanner);
	}
	
	/**
	 * 删除数据
	 * @param portalBanner
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PortalBanner portalBanner) {
		super.delete(portalBanner);
		PortalBannerContent portalBannerContent = new PortalBannerContent();
		portalBannerContent.setBannerId(portalBanner);
		portalBannerContentDao.deleteByEntity(portalBannerContent);
	}
	
}