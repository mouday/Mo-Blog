package com.mouday.blog.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mo_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String dynasty;

    @Column(name="last_login_time")
    private String lastLoginTime;

    @Column(name="create_time")
    private String createTime;

    @Column(name="update_time")
    private String updateTime;

}
