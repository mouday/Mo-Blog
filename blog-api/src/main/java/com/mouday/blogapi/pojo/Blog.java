package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Blog extends BaseEntity {
    @JsonIgnore
    private Integer userId;

    @TableField(exist = false)
    private User user;

    private String title;

    private String content;

    private Boolean isShow;

    private Date publishTime;
}