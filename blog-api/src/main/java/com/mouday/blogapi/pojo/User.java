package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

public class User extends BaseEntity {

    private String name;

    private Integer dynastyId;

    // 诗词数量，排除字段
    @TableField(exist = false)
    private Integer count;

    private Date lastLoginTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDynastyId() {
        return dynastyId;
    }

    public void setDynastyId(Integer dynastyId) {
        this.dynastyId = dynastyId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}