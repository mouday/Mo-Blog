package com.mouday.blog.service;

import com.mouday.blog.domain.Blog;
import com.mouday.blog.domain.User;
import com.mouday.blog.repository.BlogRepository;
import com.mouday.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public Page<User> getUsers(int page, int size, boolean isGetCount) {
        // org.springframework.data.mapping.PropertyReferenceException: No property create found for type User!
        // 不能使用数据库的字段名，需要使用table表字段对应的java对象字段名
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<User> users = userRepository.findAll(pageable);

        return users;
    }


}
