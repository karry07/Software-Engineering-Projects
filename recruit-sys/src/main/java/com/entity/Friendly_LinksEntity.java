package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 友情链接
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date
 */
@TableName("friendly_links")
public class Friendly_LinksEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public Friendly_LinksEntity() {
		
	}
	
	public Friendly_LinksEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 网站名称
	 */
					
	private String webname;
	
	/**
	 * logo
	 */
					
	private String logo;
	
	/**
	 * 网址
	 */
					
	private String website;
	
	/**
	 * 网站介绍
	 */
					
	private String webinfo;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：网站名称
	 */
	public void setwebname(String webname) {
		this.webname = webname;
	}
	/**
	 * 获取：网站名称
	 */
	public String getwebname() {
		return webname;
	}
	/**
	 * 设置：logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 获取：logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * 设置：网址
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * 获取：网址
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * 设置：网站介绍
	 */
	public void setwebinfo(String webinfo) {
		this.webinfo = webinfo;
	}
	/**
	 * 获取：网站介绍
	 */
	public String getwebinfo() {
		return webinfo;
	}

}
