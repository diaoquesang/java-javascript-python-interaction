package com.example.demo.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "用户登录时传的数据")
public class UserLoginDTO {
    private String username;
    private String password;
}
