package com.example.demo.controller;

import com.example.demo.result.Result;
import com.example.demo.service.CommonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class CommonController {


    @Autowired
    private CommonService commonService;

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file){

        try {
            commonService.imageProcessing(file);
            return Result.success();
        } catch (IOException e) {
            log.info("文件上传失败");
            return Result.error("文件上传失败");
        }
    }
}
