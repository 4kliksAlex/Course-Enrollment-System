package com.code.dbcourse1.service;

import com.code.dbcourse1.mapper.CourseMapper;
import com.code.dbcourse1.pojo.Course;
import com.code.dbcourse1.pojo.ResponseException;
import com.code.dbcourse1.util.CourseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private CourseMapper courseMapper;

    public String[][][] getCourseTable(long uid) {
        List<Course> courseOfStudent = courseMapper.getCourseOfStudent(uid);
        return CourseUtil.generateCourseTable(courseOfStudent);
    }

    public boolean selectCourse(long uid, long courseId) {
        Course course = courseMapper.getCourseById(courseId);
        // 查看剩余容量够不够
        // 已经选的量
        long selectedNum = courseMapper.getCourseSelectedNum(courseId);
        if(selectedNum >= course.getCapacity()){
            throw new ResponseException("该课程容量已满", false);
        }
        // 查看时间是否冲突
        String[][][] courseTable = getCourseTable(uid);
        CourseUtil.generateCourseTable(courseTable, course.getWeek(), course.getWeekday(), course.getSection(), course.getName());

        return courseMapper.studentSelectCourse(uid, courseId);
    }

    public boolean dropCourse(long uid, long courseId) {
        return courseMapper.delete(uid, courseId);
    }

    public List<Course> getCourseList(String courseId, String uid, String name) {
        List<Course> courseList = courseMapper.getCourseList(courseId, uid, name);
        // 查询剩余容量
        for (Course course : courseList) {
            course.setSelectedNum(courseMapper.getCourseSelectedNum(course.getCourseId()));
        }
        return courseList;
    }

    public List<Course> getCourseListOfStudent(long uid) {
        List<Course> courseList = courseMapper.getCourseOfStudent(uid);
        // 查询剩余容量
        // 查询剩余容量
        for (Course course : courseList) {
            course.setSelectedNum(courseMapper.getCourseSelectedNum(course.getCourseId()));
        }
        return courseList;
    }
}
