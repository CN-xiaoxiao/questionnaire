package com.xiaoxiao.web.image.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片上传控制器
 */
@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {
    // 图片上传的路径
    @Value("${web.uploadPath}")
    private String webUploadPath;

    @RequestMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        String url = null;
        // 获取文件名字
        String fileName = file.getOriginalFilename();
        // 文件扩展名
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        // 生成新的名称
        String newName = UUID.randomUUID().toString()+fileExtensionName;
        String path = webUploadPath;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            // 设置权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, newName);

        try {
            file.transferTo(targetFile);
            url = "/" + targetFile.getName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "/images" + url;
    }
}
