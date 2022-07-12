package com.jeesite.modules.portal.entity;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 首页图片内容Entity
 * @author ssg
 * @version 2022-07-12
 */
@Table(name="portal_banner", alias="a", label="首页图片内容信息", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="sort", attrName="sort", label="排序号", isUpdateForce=true),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class PortalBanner extends DataEntity<PortalBanner> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private Long sort;		// 排序号
	private List<PortalBannerContent> portalBannerContentList = ListUtils.newArrayList();		// 子表列表
	
	public PortalBanner() {
		this(null);
	}
	
	public PortalBanner(String id){
		super(id);
	}
	
	@Size(min=0, max=200, message="标题长度不能超过 200 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	@Valid
	public List<PortalBannerContent> getPortalBannerContentList() {
		return portalBannerContentList;
	}

	public void setPortalBannerContentList(List<PortalBannerContent> portalBannerContentList) {
		this.portalBannerContentList = portalBannerContentList;
	}
	
}