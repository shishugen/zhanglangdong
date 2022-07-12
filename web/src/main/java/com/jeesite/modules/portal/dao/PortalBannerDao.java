package com.jeesite.modules.portal.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.portal.entity.PortalBanner;

/**
 * 首页图片内容DAO接口
 * @author ssg
 * @version 2022-07-12
 */
@MyBatisDao
public interface PortalBannerDao extends CrudDao<PortalBanner> {
	
}