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
 * 面试信息
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("interview")
public class InterviewEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public InterviewEntity(){
    }

    public InterviewEntity(T t){
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
     * 应聘表id
     */
    private Long applicationid;

    /**
     * 面试时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date interviewtime;

    /**
     * 面试官
     */
    private String interviewer;

    /**
     * 面试地点
     */

    private String location;

    /**
     * 面试状态
     */

    private String status;

    /**
     * 面试反馈
     */

    private String feedback;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Long applicationid) {
        this.applicationid = applicationid;
    }

    public Date getInterviewtime() {
        return interviewtime;
    }

    public void setInterviewtime(Date interviewtime) {
        this.interviewtime = interviewtime;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
