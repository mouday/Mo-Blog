package com.mouday.blogapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.mapper.DynastyMapper;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.pojo.Dynasty;
import com.mouday.blogapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynastyService {
    @Autowired
    DynastyMapper dynastyMapper;

    /**
     * 获取用户列表

     */
    public List<Dynasty> getAll(){

        List<Dynasty> list = dynastyMapper.selectList(null);

        return list;
    }

    public List<Dynasty> getAllDynastyWithUserCount(){
        return dynastyMapper.getAllDynastyWithUserCount();
    }

    public int deleteById(Integer id){
        return dynastyMapper.deleteById(id);
    }

    public Dynasty getDynastyById(Integer id) {
        return dynastyMapper.selectById(id);
    }

    public Dynasty saveDynasty(Dynasty dynasty) {

        if (dynasty.getId() == null) {
            dynastyMapper.insert(dynasty);
        } else {
            dynastyMapper.updateDynastyById(dynasty);
        }

        return dynasty;
    }

}
