package com.jeesite.modules.portal.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 首页图片内容Entity
 * @author ssg
 * @version 2022-07-12
 */
@Table(name="portal_banner_content", alias="a", label="首页图片内容信息", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="banner_id", attrName="bannerId.id", label="banneID"),
		@Column(name="content", attrName="content", label="内容"),
		@Column(name="sort", attrName="sort", label="排序号", isUpdateForce=true),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class PortalBannerContent extends DataEntity<PortalBannerContent> {
	
	private static final long serialVersionUID = 1L;
	private PortalBanner bannerId;		// banneID 父类
	private String content;		// 内容
	private Long sort;		// 排序号
	
	public PortalBannerContent() {
		this(null);
	}

	public PortalBannerContent(PortalBanner bannerId){
		this.bannerId = bannerId;
	}
	
	public PortalBanner getBannerId() {
		return bannerId;
	}

	public void setBannerId(PortalBanner bannerId) {
		this.bannerId = bannerId;
	}
	
	@Size(min=0, max=200, message="内容长度不能超过 200 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}