package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

// @TableName(value = "mo_user")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String dynasty;

    // 诗词数量，排除字段
    @TableField(exist = false)
    private Integer count;

    private Date lastLoginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}