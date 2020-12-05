package com.mouday.blog.repository;

import com.mouday.blog.domain.Blog;
import com.mouday.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Integer>, JpaSpecificationExecutor<Blog> {
    // 使用left join 解决N+1次查询问题
    // 解决Springboot+JPA中多表关联查询会查询多次的问题（n+1查询问题）
    // https://www.cnblogs.com/wanbao/p/10265620.html
    @EntityGraph(value = "Blog.user", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    Page<Blog> findAll(Pageable pageable);
}
