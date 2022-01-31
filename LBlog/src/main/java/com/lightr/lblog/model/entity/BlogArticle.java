package com.lightr.lblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Lightr
 * @since 2022-01-20
 */
@TableName("blog_article")
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userid;
    @NotBlank(message = "title为空")
    private String title;
    @NotBlank(message = "描述为空")
    private String description;
    @NotBlank(message = "内容为空")
    private String content;

    private String createtime;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BlogArticle{" +
                "id=" + id +
                ", userid=" + userid +
                ", title=" + title +
                ", description=" + description +
                ", content=" + content +
                ", createtime=" + createtime +
                ", status=" + status +
                "}";
    }
}
