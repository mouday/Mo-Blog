package com.mouday.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "mo_blog")
@NamedEntityGraph(name = "Blog.user", attributeNodes = {@NamedAttributeNode("user")})
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    // @Column(name = "user_id", insertable = false, updatable = false)
    // private Integer userId;

    // No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor
    // and no properties discovered to create BeanSerializer
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    private String title;

    private String content;

    @Column(name="create_time")
    private String createTime;

    @Column(name="update_time")
    private String updateTime;

}
