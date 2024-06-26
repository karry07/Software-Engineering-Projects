package com.entity.vo;

import java.io.Serializable;

public class ForumVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userid;

    private String title;

    private String content;

    private Integer thumbsupnum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getThumbsupnum() {
        return thumbsupnum;
    }

    public void setThumbsupnum(Integer thumbsupnum) {
        this.thumbsupnum = thumbsupnum;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
