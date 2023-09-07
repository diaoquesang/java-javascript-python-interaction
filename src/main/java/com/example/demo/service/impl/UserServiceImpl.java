package com.example.demo.service.impl;


import com.example.demo.exception.AccountLockedException;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.PasswordErrorException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.dto.UserLoginDTO;
import com.example.demo.pojo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        User user = userMapper.getByUsername(username);

        if (user == null){

            //注册员工代码
            /*user = User.builder()
                    .username(username)
                    .password(password)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);*/

        throw new AccountNotFoundException("用户不存在");
        }

        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException("密码错误");
        }

        if (user.getStatus() == 0) {
            //账号被锁定
            throw new AccountLockedException("账号被锁定");
        }

        return user;
    }
}
