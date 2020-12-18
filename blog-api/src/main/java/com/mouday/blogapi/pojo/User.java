package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class User extends BaseEntity {

    private String name;

    private Integer dynastyId;

    @TableField(exist = false)
    private Integer roleId;

    @TableField(exist = false)
    private List<Permission> permissions;

    // 诗词数量，排除字段
    @TableField(exist = false)
    private Integer count;

    private Date lastLoginTime;

}