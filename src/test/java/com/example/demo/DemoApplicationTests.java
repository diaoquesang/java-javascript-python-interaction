package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {

    @Test
    public void test(){
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String absolutePath = applicationHome.getDir().getAbsolutePath();
        System.out.println(absolutePath);
    }

}
