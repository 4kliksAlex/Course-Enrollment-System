package com.code.dbcourse1.service;

import com.code.dbcourse1.mapper.UserMapper;
import com.code.dbcourse1.pojo.ResponseException;
import com.code.dbcourse1.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GlobalService {
    @Resource
    private UserMapper userMapper;

    public boolean register(String name, String password, String role, String sex, int age, String tel, String email) {
        // 查询 name + password 存不存在
        if(userMapper.getUserByNameAndPassword(name, password) != null){
            throw new ResponseException("注册失败, 密码不合法, 请更换密码重试", false);
        }
        return userMapper.register(name, password, role, sex, age, tel, email);
    }

    public User login(String name, String password) {
        return userMapper.getUserByNameAndPassword(name, password);
    }
}
