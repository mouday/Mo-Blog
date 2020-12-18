package com.mouday.blogapi.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPassword extends BaseEntity {

    @TableId(type = IdType.INPUT)
    protected Integer id;

    @TableField(exist = false)
    private String username;

    private String password;

}