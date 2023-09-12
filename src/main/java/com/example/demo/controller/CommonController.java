package com.example.demo.controller;

import com.example.demo.service.CommonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class CommonController {


    @Autowired
    private CommonService commonService;

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    public void upload(MultipartFile file, HttpServletResponse response){

        try {
            commonService.imageProcessing(file, response);
        } catch (IOException e) {
            log.info("文件上传失败：{}", file);
        }
    }
}
