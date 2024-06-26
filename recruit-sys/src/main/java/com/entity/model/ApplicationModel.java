package com.entity.model;

import java.io.Serializable;
 

/**
 * 应聘信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date
 */
public class ApplicationModel  implements Serializable {
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
	 * 用户名
	 */
	
	private String username;
		
	/**
	 * 姓名
	 */
	
	private String fullname;
		
	/**
	 * 电话
	 */
	
	private String phone;
		
	/**
	 * 简历
	 */
	
	private String resume;
		
	/**
	 * 是否审核
	 */
	
	private String ifaudit;
		
	/**
	 * 审核回复
	 */
	
	private String auditresponse;

	/**
	 * 状态
	 */

	private String status;
	
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
	 * 设置：用户名
	 */
	 
	public void setUsername(String userName) {
		this.username = userName;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setFullname(String fullName) {
		this.fullname = fullName;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getFullname() {
		return fullname;
	}
				
	
	/**
	 * 设置：电话
	 */
	 
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
				
	
	/**
	 * 设置：简历
	 */
	 
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	/**
	 * 获取：简历
	 */
	public String getResume() {
		return resume;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setIfaudit(String ifaudit) {
		this.ifaudit = ifaudit;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getIfaudit() {
		return ifaudit;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setAuditresponse(String auditresponse) {
		this.auditresponse = auditresponse;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getAuditresponse() {
		return auditresponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
