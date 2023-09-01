package com.example.demo.service;

import com.example.demo.pojo.dto.UserLoginDTO;
import com.example.demo.pojo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(UserLoginDTO userLoginDTO);
}
