package com.mouday.blogapi.service;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.result.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 支持文件上传
 */
@Service
public class FileService {

    @Value("${server.port}")
    private String port;

    // 文件上传目录，'public/upload'
    private final String FILE_DIR = "/upload";

    // 转换为自定义的文件名
    private String getFileName(String originalFilename){
        // 获取扩展名
        String ext = originalFilename.substring(originalFilename.indexOf("."));

        // 设置文件名
        String filename = UUID.randomUUID().toString() + ext;

        return filename;
    }

    private String getFileUrl(String filename){
        return "http://localhost:" + this.port + filename;
    }

    public String upload(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new BaseException(ResultCode.PARAM_IS_BLANK);
        }

        String filename = this.getFileName(file.getOriginalFilename());
        File path = this.getUploadDirectory();

        // 设置保存路径
        File dest = new File(path, filename);

        file.transferTo(dest);

        // 返回可访问路径
        String fileUrl = this.getFileUrl(new File(this.FILE_DIR, filename).toString());

        return fileUrl;

    }

    /**
     * 获取文件保存路径
     * 参考：https://www.bbsmax.com/A/GBJrE67Wz0/
     *
     * @return
     * @throws FileNotFoundException
     */
    public File getUploadDirectory() throws FileNotFoundException {
        String pathName = ResourceUtils.getURL("classpath:").getPath();
        File path = new File(pathName, "/public" + this.FILE_DIR);

        if (!path.exists()) {
            path.mkdirs();
        }

        return path;
    }

}
