package com.code.dbcourse1.controller;

import com.code.dbcourse1.pojo.Course;
import com.code.dbcourse1.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 发表课程
     * @param uid 教师id
     * @param name 课程名
     * @param capacity 容量
     * @param remark 备注
     * @param week 上课时间-周
     * @param weekday 上课时间-星期几
     * @param section 上课时间-第几节
     * @return 添加成功返回true
     */
    @PostMapping("/publishCourse")
    public boolean publishCourse(
            @RequestParam long uid,
            @RequestParam String name,
            @RequestParam  long capacity,
            @RequestParam String remark,
            @RequestParam String week,
            @RequestParam String weekday,
            @RequestParam String section){
        return teacherService.publishCourse(uid, name, capacity, remark, week, weekday, section);
    }

    /**
     * 查看老师的课表
     * @param uid 教师id
     * @return 生成的课表 rst[i][j][k] 表示第i周星期j第k节是否有课, 有课为课名, 没课为null
     */
    @GetMapping("/getCourseTable")
    public String[][][] getCourseTable(@RequestParam long uid){
        return teacherService.getCourseTable(uid);
    }

    /**
     * 扩课
     * @param uid 教师号
     * @param courseId 课程号
     * @param newCapacity 新容量 必须大于原先容量
     * @return 操作成功返回true
     */
    @PostMapping("/expandCourse")
    public boolean expandCourse(@RequestParam long uid, @RequestParam long courseId, @RequestParam long newCapacity){
        return teacherService.expandCourse(uid, courseId, newCapacity);
    }

    @GetMapping("/getCourseList")
    public List<Course> getCourseList(@RequestParam long uid){
        return teacherService.getCourseList(uid);
    }
}
