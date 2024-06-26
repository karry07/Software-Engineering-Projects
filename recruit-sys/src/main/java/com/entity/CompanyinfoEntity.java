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
 * 企业信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date
 */
@TableName("companyinfo")
public class CompanyinfoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CompanyinfoEntity() {
		
	}
	
	public CompanyinfoEntity(T t) {
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
	 * 企业编号
	 */
					
	private String companynumber;
	
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
	 * 设置：企业编号
	 */
	public void setCompanynumber(String companynumber) {
		this.companynumber = companynumber;
	}
	/**
	 * 获取：企业编号
	 */
	public String getCompanynumber() {
		return companynumber;
	}
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
