package com.mouday.blogapi.controller;

import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResultController
public class FileController {
    @Autowired
    FileService fileService;

    // 单文件上传
    @PostMapping("/uploadFile")
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        String filename = fileService.upload(file);

        Map<String, Object> map = new HashMap<>();
        map.put("url", filename);
        return map;

    }

    // 多文件上传
    @PostMapping("/multiUpload")
    public Map<String, Object> upload(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<String> list = new ArrayList<>();

        for (MultipartFile file : files) {
            list.add(fileService.upload(file));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("urls", list);

        return map;
    }
}
