package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommonService {
    void imageProcessing(MultipartFile file) throws IOException;
}
