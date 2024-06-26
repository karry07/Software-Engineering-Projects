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
 * 求职者信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date
 */
@TableName("jobseekerinfo")
public class JobseekerinfoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JobseekerinfoEntity() {
		
	}
	
	public JobseekerinfoEntity(T t) {
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
	 * 用户名
	 */
					
	private String username;
	
	/**
	 * 姓名
	 */
					
	private String fullname;
	
	/**
	 * 性别
	 */
					
	private String gender;
	
	/**
	 * 电话
	 */
					
	private String phone;
	
	/**
	 * 照片
	 */
					
	private String photo;
	
	/**
	 * 学历
	 */
					
	private String education;
	
	/**
	 * 岗位类别
	 */
					
	private String jobcategory;
	
	/**
	 * 简历
	 */
					
	private String resume;
	
	/**
	 * 工作经历
	 */
					
	private String workexperience;
	
	/**
	 * 个人基本情况
	 */
					
	private String personalinfo;
	
	/**
	 * 赞
	 */
					
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
					
	private Integer crazilynum;

	/**
	 * 出生日期
	 */
	private Date birthdate;
	/**
	 * 政治面貌
	 **/
	private String politicalstatus;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 籍贯
	 */
	private String birthplace;

	/**
	 * 民族
	 */
	private String ethnicity;

	/**
	 * 身高
	 */
	private Double height;

	/**
	 * 邮箱
	 */
	private String email;


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
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
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
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
	 * 获取：姓名
	 */
	public String getFullname() {
		return fullname;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
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
	 * 设置：照片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：照片
	 */
	public String getPhoto() {
		return photo;
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
	 * 设置：工作经历
	 */
	public void setWorkexperience(String workexperience) {
		this.workexperience = workexperience;
	}
	/**
	 * 获取：工作经历
	 */
	public String getWorkexperience() {
		return workexperience;
	}
	/**
	 * 设置：个人基本情况
	 */
	public void setPersonalinfo(String personalinfo) {
		this.personalinfo = personalinfo;
	}
	/**
	 * 获取：个人基本情况
	 */
	public String getPersonalinfo() {
		return personalinfo;
	}
	/**
	 * 设置：赞
	 */
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
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
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * 获取：出生日期
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * 设置：政治面貌
	 */
	public void setPoliticalstatus(String politicalstatus) {
		this.politicalstatus = politicalstatus;
	}
	/**
	 * 获取：政治面貌
	 */
	public String getPoliticalstatus() {
		return politicalstatus;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置：籍贯
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * 获取：籍贯
	 */
	public String getBirthplace() {
		return birthplace;
	}

	/**
	 * 设置：民族
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	/**
	 * 获取：民族
	 */
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 * 设置：身高
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * 获取：身高
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}

}