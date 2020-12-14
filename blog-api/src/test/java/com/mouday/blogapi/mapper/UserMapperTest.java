package com.mouday.blogapi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testGetUserList(){
        Page<Map<String, Object>> page = new Page<>(1, 2, false);
        // IPage<User> iPage = userMapper.selectUserWithBlogCount(page);

        // System.out.println(iPage.getTotal());
        // System.out.println(iPage.getRecords());

    }

}
