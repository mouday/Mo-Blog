package com.mouday.blogapi.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Permission {

    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

}