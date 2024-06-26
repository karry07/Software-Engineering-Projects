package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class InterviewModel  implements Serializable {

    private static final long serialVersionUID = 1L;
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
