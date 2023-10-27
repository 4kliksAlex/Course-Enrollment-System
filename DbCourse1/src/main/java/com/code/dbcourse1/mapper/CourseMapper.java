package com.code.dbcourse1.mapper;


import com.code.dbcourse1.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {

    @Select("select * from course")
    List<Course> getAllCourse();

    @Select("select * from course where uid=#{uid}")
    List<Course> getCourseListOfTeacher(@Param("uid") long uid);

    @Select("select course.* from student_course" +
            "            join course on student_course.course_id = course.course_id" +
            "        where student_course.uid=#{uid}")
    List<Course> getCourseOfStudent(@Param("uid") long uid);

    @Insert("insert into course(uid, `name`, capacity, remarks, `week`, weekday, section) values (#{uid}, #{name}, #{capacity}, #{remark}, #{week}, #{weekday}, #{section})")
    boolean insert(
            @Param("uid") long uid,
            @Param("name") String name,
            @Param("capacity") long capacity,
            @Param("remark") String remark,
            @Param("week") String week,
            @Param("weekday") String weekday,
            @Param("section") String section
    );


    @Select("select * from course where uid=#{uid} and course_id=#{courseId}")
    Course getCourseOfTeacher(@Param("uid") long uid, @Param("courseId") long courseId);

    @Update("update course set capacity=#{newCapacity} where uid=#{uid} and course_id=#{courseId}")
    boolean updateCapacity(@Param("uid") long uid, @Param("courseId") long courseId, @Param("newCapacity") long newCapacity);

    @Select("select * from course where course_id=#{courseId}")
    Course getCourseById(@Param("courseId") long courseId);

    @Insert("insert into student_course(uid, course_id) values (#{uid}, #{courseId})")
    boolean studentSelectCourse(@Param("uid") long uid, @Param("courseId") long courseId);

    @Select("select count(*) from student_course where course_id=#{courseId}")
    long getCourseSelectedNum(@Param("courseId") long courseId);

    @Delete("delete from student_course where uid=#{uid} and course_id=#{courseId}")
    boolean delete(long uid, long courseId);

    @Select("select * from course " +
            "where course_id like CONCAT('%', #{courseId}, '%') " +
            "and uid like CONCAT('%', #{uid}, '%') " +
            " and name like CONCAT('%', #{name}, '%') ")
    List<Course> getCourseList(String courseId, String uid, String name);
}
