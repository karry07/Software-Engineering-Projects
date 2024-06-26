package com.entity.model;

import java.io.Serializable;

/**
 * 论坛
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author
 * @email
 * @date
 */
public class ForumModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Long userid;
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
