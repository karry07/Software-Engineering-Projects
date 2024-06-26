package com.entity.vo;

import com.entity.Friendly_LinksEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 友情链接
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date
 */
public class Friendly_LinksVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
