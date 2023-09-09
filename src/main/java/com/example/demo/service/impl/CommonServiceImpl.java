package com.example.demo.service.impl;

import com.example.demo.context.BaseContext;
import com.example.demo.exception.ProcessingFailedException;
import com.example.demo.service.CommonService;
import com.example.demo.utils.GetPathUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private GetPathUtil getPathUtil;

    @Override
    public String imageProcessing(MultipartFile file) throws IOException {
        //存储图片
        Long userId = BaseContext.getCurrentId();
        LocalDateTime now = LocalDateTime.now();
        String originalFilename = file.getOriginalFilename();

        assert originalFilename != null;
        String fileName = userId.toString() + "_"
                + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))
                + originalFilename.substring(originalFilename.lastIndexOf('.'));

        String savePath = getPathUtil.getSavePath();
        file.transferTo(new File(savePath, fileName));

        //将状态写入excel
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("templates/文件状态.xlsx");

        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = excel.getSheet("Sheet1");

        int serialNumber = 1;
        while (true){
            XSSFRow row = sheet.getRow(serialNumber);
            XSSFCell cell = row.getCell(0);

            if (cell.getStringCellValue() != null){
                cell.setCellValue(serialNumber);

                cell = row.getCell(1);
                cell.setCellValue(fileName);

                cell = row.getCell(2);
                cell.setCellValue(userId);

                cell = row.getCell(3);
                cell.setCellValue(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")));

                cell = row.getCell(4);
                cell.setCellValue("否");
                break;
            }
            serialNumber += 1;
        }


        //发送HTTP请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8081/process");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        //处理完成，写入excel
        if (statusCode == 200){
            XSSFRow row = sheet.getRow(serialNumber);
            XSSFCell cell = row.getCell(4);
            cell.setCellValue("是");

            return getPathUtil.getLoadPath();
        }else {
            throw new ProcessingFailedException("处理失败");
        }
    }
}
