package com.mouday.blog.service;

import com.mouday.blog.domain.User;
import com.mouday.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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


}
