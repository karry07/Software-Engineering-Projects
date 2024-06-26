package com.entity.model;

import java.io.Serializable;
 

/**
 * 友情链接
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date
 */
public class Friendly_LinksModel  implements Serializable {
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
