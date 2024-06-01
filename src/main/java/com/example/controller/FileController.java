package com.example.controller;

import com.example.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileController {

    @PostMapping("/upload")
    public Result<String> upload( MultipartFile file) throws IOException {
        //把文件存在本地端
        String originalFilename = file.getOriginalFilename();
        //保證文件的名字是唯一 防止覆蓋
        String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("E:\\upload\\"+fileName));
        return Result.success("文件位址");
    }
}
