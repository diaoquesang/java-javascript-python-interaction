package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonService {
    void imageProcessing(MultipartFile file) throws IOException;
}
