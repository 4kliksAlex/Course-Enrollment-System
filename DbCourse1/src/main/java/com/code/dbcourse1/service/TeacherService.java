package com.code.dbcourse1.service;

import com.code.dbcourse1.mapper.CourseMapper;
import com.code.dbcourse1.pojo.Course;
import com.code.dbcourse1.pojo.ResponseException;
import com.code.dbcourse1.util.CourseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    private CourseMapper courseMapper;

    // 得到老师的课表
    public String[][][] getCourseTable(long uid){
        List<Course> courseOfTeacher = courseMapper.getCourseListOfTeacher(uid);
        return CourseUtil.generateCourseTable(courseOfTeacher);
    }

    public boolean publishCourse(long uid, String name, long capacity, String remark, String week, String weekday, String section) {
        // 判断上课时间是否冲突
        String[][][] courseTable = getCourseTable(uid);
        courseTable = CourseUtil.generateCourseTable(courseTable, week, weekday, section, name);

        return courseMapper.insert(uid, name, capacity, remark, week, weekday, section);
    }

    public boolean expandCourse(long uid, long courseId, long newCapacity) {
        // 判断课程是不是存在
        Course course = courseMapper.getCourseOfTeacher(uid, courseId);
        if(course == null){
            throw new ResponseException("权限不足", false);
        }
        // 判断新容量是不是大于旧容量
        if(course.getCapacity() >= newCapacity){
            throw new ResponseException("新容量必须大于旧容量", false);
        }
        return courseMapper.updateCapacity(uid, courseId, newCapacity);
    }

    public List<Course> getCourseList(long uid) {
        List<Course> courseList = courseMapper.getCourseListOfTeacher(uid);
        // 查询剩余容量
        // 查询剩余容量
        for (Course course : courseList) {
            course.setSelectedNum(courseMapper.getCourseSelectedNum(course.getCourseId()));
        }
        return courseList;
    }
}
