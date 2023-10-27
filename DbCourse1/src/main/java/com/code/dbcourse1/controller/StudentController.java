package com.code.dbcourse1.controller;

import com.code.dbcourse1.pojo.Course;
import com.code.dbcourse1.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 查看学生的课表
     * @param uid 学号
     * @return 生成的课表 rst[i][j][k] 表示第i周星期j第k节是否有课, 有课为课名, 没课为null
     */
    @GetMapping("/getCourseTable")
    public String[][][] getCourseTable(@RequestParam long uid){
        return studentService.getCourseTable(uid);
    }

    /**
     * 选课
     * @param uid 学号
     * @param courseId 姓名
     * @return 选课成功返回true
     */
    @PostMapping("/selectCourse")
    public boolean selectCourse(@RequestParam long uid, @RequestParam long courseId){
        return studentService.selectCourse(uid, courseId);
    }

    /**
     * 退课
     * @param uid 学号
     * @param courseId 姓名
     * @return 退课成功返回true
     */
    @PostMapping("/dropCourse")
    public boolean dropCourse(@RequestParam long uid, @RequestParam long courseId){
        return studentService.dropCourse(uid, courseId);
    }

    /**
     * 查询课程
     */
    @GetMapping("/getCourseList")
    public List<Course> getCourseList(String courseId, String uid, String name){
        return studentService.getCourseList(courseId, uid, name);
    }

    /**
     * 查询学生的课程
     */
    @GetMapping("/getCourseListOfStudent")
    public List<Course> getCourseListOfStudent(long uid){
        return studentService.getCourseListOfStudent(uid);
    }
}
