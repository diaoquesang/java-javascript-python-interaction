package com.example.demo.controller;

import com.example.demo.context.BaseContext;
import com.example.demo.result.Result;
import com.example.demo.service.CommonService;
import com.example.demo.utils.GetPathUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
public class CommonController {


    @Autowired
    private CommonService commonService;

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){

        try {
            commonService.imageProcessing(file);
        } catch (IOException e) {
            log.info("文件上传失败：{}", file);
        }
        return Result.error("文件上传失败");
    }
}
