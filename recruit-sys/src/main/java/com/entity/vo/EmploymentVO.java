package com.entity.vo;

import java.io.Serializable;
 

/**
 * 招聘信息
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date
 */
public class EmploymentVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 企业名称
	 */
	
	private String companyname;
		
	/**
	 * 负责人
	 */
	
	private String personincharge;
		
	/**
	 * 联系电话
	 */
	
	private String contactphone;
		
	/**
	 * 招聘岗位
	 */
	
	private String position;
		
	/**
	 * 招聘人数
	 */
	
	private String recruitnum;
		
	/**
	 * 岗位类别
	 */
	
	private String jobcategory;
		
	/**
	 * 学历
	 */
	
	private String education;
		
	/**
	 * 图片
	 */
	
	private String picture;
		
	/**
	 * 岗位要求
	 */
	
	private String demand;
		
	/**
	 * 工作职责
	 */
	
	private String duty;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
				
	
	/**
	 * 设置：企业名称
	 */
	 
	public void setCompanyname(String companyName) {
		this.companyname = companyName;
	}
	
	/**
	 * 获取：企业名称
	 */
	public String getCompanyname() {
		return companyname;
	}
				
	
	/**
	 * 设置：负责人
	 */
	 
	public void setPersonincharge(String personInCharge) {
		this.personincharge = personInCharge;
	}
	
	/**
	 * 获取：负责人
	 */
	public String getPersonincharge() {
		return personincharge;
	}
				
	
	/**
	 * 设置：联系电话
	 */
	 
	public void setContactphone(String contactPhone) {
		this.contactphone = contactPhone;
	}
	
	/**
	 * 获取：联系电话
	 */
	public String getContactphone() {
		return contactphone;
	}
				
	
	/**
	 * 设置：招聘岗位
	 */
	 
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取：招聘岗位
	 */
	public String getPosition() {
		return position;
	}
				
	
	/**
	 * 设置：招聘人数
	 */
	 
	public void setRecruitnum(String recruitnum) {
		this.recruitnum = recruitnum;
	}
	
	/**
	 * 获取：招聘人数
	 */
	public String getRecruitnum() {
		return recruitnum;
	}
				
	
	/**
	 * 设置：岗位类别
	 */
	 
	public void setjobcategory(String jobcategory) {
		this.jobcategory = jobcategory;
	}
	
	/**
	 * 获取：岗位类别
	 */
	public String getjobcategory() {
		return jobcategory;
	}
				
	
	/**
	 * 设置：学历
	 */
	 
	public void setEducation(String education) {
		this.education = education;
	}
	
	/**
	 * 获取：学历
	 */
	public String getEducation() {
		return education;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * 获取：图片
	 */
	public String getPicture() {
		return picture;
	}
				
	
	/**
	 * 设置：岗位要求
	 */
	 
	public void setDemand(String demand) {
		this.demand = demand;
	}
	
	/**
	 * 获取：岗位要求
	 */
	public String getDemand() {
		return demand;
	}
				
	
	/**
	 * 设置：工作职责
	 */
	 
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	/**
	 * 获取：工作职责
	 */
	public String getDuty() {
		return duty;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsUpNum) {
		this.thumbsupnum = thumbsUpNum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilyNum) {
		this.crazilynum = crazilyNum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
			
}
