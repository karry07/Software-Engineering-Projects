package com.entity.vo;

import com.entity.CompanyinfoEntity;

import java.io.Serializable;
 

/**
 * 企业信息
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date
 */
public class CompanyinfoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 密码
	 */
	
	private String password;
		
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
	 * 企业邮箱
	 */
	
	private String companyemail;
		
	/**
	 * 企业介绍
	 */
	
	private String companyintroduction;
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
				
	
	/**
	 * 设置：企业名称
	 */
	 
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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
	 
	public void setPersonincharge(String personincharge) {
		this.personincharge = personincharge;
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
	 
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	
	/**
	 * 获取：联系电话
	 */
	public String getContactphone() {
		return contactphone;
	}
				
	
	/**
	 * 设置：企业邮箱
	 */
	 
	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}
	
	/**
	 * 获取：企业邮箱
	 */
	public String getCompanyemail() {
		return companyemail;
	}
				
	
	/**
	 * 设置：企业介绍
	 */
	 
	public void setCompanyintroduction(String companyintroduction) {
		this.companyintroduction = companyintroduction;
	}
	
	/**
	 * 获取：企业介绍
	 */
	public String getCompanyintroduction() {
		return companyintroduction;
	}
			
}
