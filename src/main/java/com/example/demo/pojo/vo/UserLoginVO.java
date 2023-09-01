package com.example.demo.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(description = "用户登录时返回的数据模型")
@Builder
public class UserLoginVO {
    private long id;
    private String username;
    private String password;
    private String token;
}
