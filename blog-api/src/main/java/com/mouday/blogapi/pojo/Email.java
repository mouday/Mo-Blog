package com.mouday.blogapi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email extends BaseEntity{

    private String toUser;

    private String subject;

    private String content;

    @JsonProperty("isSend")
    private boolean isSend;

}