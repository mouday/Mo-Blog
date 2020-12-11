package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseEntity {

    private String name;

    private String dynasty;

    // 诗词数量，排除字段
    @TableField(exist = false)
    private Integer count;

    private Date lastLoginTime;

}