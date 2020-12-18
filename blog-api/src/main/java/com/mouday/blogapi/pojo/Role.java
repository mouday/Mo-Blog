package com.mouday.blogapi.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Role extends BaseEntity {

    private String name;

    @TableField(exist = false)
    private List<Permission> permissions;


}