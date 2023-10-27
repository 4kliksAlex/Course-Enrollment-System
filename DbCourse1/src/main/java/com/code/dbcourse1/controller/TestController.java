package com.code.dbcourse1.controller;

import com.code.dbcourse1.mapper.CourseMapper;
import com.code.dbcourse1.mapper.UserMapper;
import com.code.dbcourse1.pojo.Course;
import com.code.dbcourse1.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CourseMapper courseMapper;

    @RequestMapping("/test")
    public List<Course> test(){
        return courseMapper.getAllCourse();
    }
}
