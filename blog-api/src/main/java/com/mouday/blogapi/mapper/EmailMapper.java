package com.mouday.blogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mouday.blogapi.pojo.Email;

public interface EmailMapper extends BaseMapper<Email> {
    void updateSendStatusById(Integer id, boolean status);
}