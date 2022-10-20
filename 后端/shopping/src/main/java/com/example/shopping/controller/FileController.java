package com.example.shopping.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: SilenceOu
 * @Date: 2022/10/20 19:51
 */
@RestController
@RequestMapping("/file")
public class FileController {
    //将该文件夹的路径赋值给fileUploadPath
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload/img")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);       //后缀
        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        //文件路径与下载接口路径一样
        String url = "http://localhost:8010/api/file/" + fileUUID;
        //把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);
        return url;
    }
}
