package com.mouday.blogapi.controller;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.pojo.Dynasty;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.DynastyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResultController
public class RoleController {
    @Autowired
    DynastyService dynastyService;

    @GetMapping("/dynastyList")
    public List<Dynasty> getDynastyList() {
        return dynastyService.getAll();
    }


    @GetMapping("/dynasty")
    public Dynasty getDynasty(@RequestParam Integer id) {
        return dynastyService.getDynastyById(id);
    }

    @GetMapping("/dynastyWithUserCount")
    public List<Dynasty> getDynastyWithUserCount() {
        return dynastyService.getAllDynastyWithUserCount();
    }

    @DeleteMapping("/dynasty")
    public void deleteDynasty(@RequestParam Integer id) {
        int ret = dynastyService.deleteById(id);

        if(ret == 0){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }
    }

    @PostMapping("/dynasty")
    public Dynasty saveDynasty(@RequestBody Dynasty dynasty) {
        return dynastyService.saveDynasty(dynasty);
    }

}
