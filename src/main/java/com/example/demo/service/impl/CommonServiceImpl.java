package com.example.demo.service.impl;

import com.example.demo.exception.ProcessingFailedException;
import com.example.demo.service.CommonService;
import com.example.demo.utils.GetPathUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private GetPathUtil getPathUtil;

    @Override
    public void imageProcessing(MultipartFile file) throws IOException {
        //存储图片
        //TODO 此为获取用户id
        //Long userId = BaseContext.getCurrentId();
        Long userId = 1L;
        LocalDateTime now = LocalDateTime.now();
        String originalFilename = file.getOriginalFilename();

        assert originalFilename != null;
        String fileName = userId.toString() + "_"
                + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))
                + originalFilename.substring(originalFilename.lastIndexOf('.'));
        //TODO 保存路径应改为动态而不是动态
        //String savePath = getPathUtil.getSavePath();
        String savePath = "D:\\Develop\\IdeaProjects\\demo\\src\\main\\resources\\static\\original";
        file.transferTo(new File(savePath, fileName));

        //将状态写入excel
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("templates/文件状态.xlsx");
        //TODO 保存路径应改为动态
        OutputStream outputStream = Files.newOutputStream(new File("D:\\Develop\\IdeaProjects\\demo\\src\\main\\resources\\templates\\文件状态.xlsx").toPath());

        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = excel.getSheet("Sheet1");

        int serialNumber = 1;
        while (true){
            XSSFRow row = sheet.getRow(serialNumber);
            XSSFCell cell = row.getCell(0);

            if (cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty()){
                cell.setCellValue(serialNumber);

                cell = row.getCell(1);
                cell.setCellValue(fileName);

                cell = row.getCell(2);
                cell.setCellValue(userId);

                cell = row.getCell(3);
                cell.setCellValue(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")));

                cell = row.getCell(4);
                cell.setCellValue("否");

                excel.write(outputStream);
                break;
            }
            serialNumber += 1;
        }


        //TODO 发送HTTP请求
        /*CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8081/process");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();*/

        int statusCode = 200;

        //处理完成，写入excel
        if (statusCode == 200){
            XSSFRow row = sheet.getRow(serialNumber);
            XSSFCell cell = row.getCell(4);
            cell.setCellValue("是");

            //InputStream in = new FileInputStream(new File(getPathUtil.getLoadPath(), fileName));
            /*resp.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.copy(in, resp.getOutputStream());*/
        }else {
            throw new ProcessingFailedException("处理失败");
        }
        inputStream.close();
        outputStream.close();
        excel.close();
    }
}
