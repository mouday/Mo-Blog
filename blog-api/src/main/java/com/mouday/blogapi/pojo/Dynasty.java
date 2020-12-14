package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Dynasty extends BaseEntity {
    private String name;

    @TableField(exist = false)
    private Integer userCount;
}