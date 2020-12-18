package com.mouday.blogapi.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailEntity extends BaseEntity{

    private String toUser;

    private String subject;

    private String content;

    private boolean isSend;

}