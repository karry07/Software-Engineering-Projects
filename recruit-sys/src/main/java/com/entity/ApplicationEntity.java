package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 应聘信息
 * 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date
 */
@TableName("application")
public class ApplicationEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ApplicationEntity() {

    }

    public ApplicationEntity(T t) {
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
     * 企业账号
     */

    private String companynumber;

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
     * 是否审核通过
     */

    private String ifaudit;

    /**
     * 审核回复
     */

    private String auditresponse;

    /**
     * 添加时间
     */

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    /**
     * 更新时间
     */

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date updatetime;

    /**
     * 状态
     */

    private String status;

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }


    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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
    public void setCompanynumber(String companyNumber) {
        this.companynumber = companyNumber;
    }

    /**
     * 获取：企业编号
     */
    public String getCompanynumber() {
        return companynumber;
    }

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