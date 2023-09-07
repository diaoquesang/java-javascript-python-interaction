package com.example.demo.utils;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;

@Component
public class GetPathUtil {
    public String getSavePath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        // 保存目录位置根据项目需求可随意更改
        return applicationHome.getDir().getAbsolutePath() + "\\src\\main\\resources\\static\\original";
    }

    public String getLoadPath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        // 保存目录位置根据项目需求可随意更改
        return applicationHome.getDir().getAbsolutePath() + "\\src\\main\\resources\\static\\processed";
    }
}
