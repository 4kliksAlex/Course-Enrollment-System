package com.code.dbcourse1.util;

import com.code.dbcourse1.pojo.Course;
import com.code.dbcourse1.pojo.ResponseException;

import java.util.List;

public class CourseUtil {
    /**
     * 生成课表
     * @param curCourseTable 已经有课组成的课表
     * @param week 新课开的周
     * @param weekday 新课开的星期几
     * @param section 新课开的第几节
     * @param name 课程名
     * @return 生成的课表 rst[i][j][k] 表示第i周星期j第k节是否有课, 有课为课名, 没课为null
     */
    public static String[][][] generateCourseTable(
            String[][][] curCourseTable,
            String week,
            String weekday,
            String section,
            String name
            ){
        if(curCourseTable == null){
            curCourseTable = new String[10][7][12]; // 10周 1周7天 1天12节课
        }
        if( week == null || weekday == null || section == null){
            System.out.println(" ------- generateCourseTable");
            System.out.println(week);
            System.out.println(weekday);
            System.out.println(section);
            throw new RuntimeException("参数不能为空");
        }
        // String里 1表示有课 0 表示没课
        for(int i = 0; i < week.length(); ++i){
            if(week.charAt(i) == '1'){
                for(int j = 0; j < weekday.length(); ++j){
                    if(weekday.charAt(j) == '1'){
                        for(int k = 0; k < section.length(); ++k){
                            if(section.charAt(k) == '1'){
                                // 新课 第i周 星期j 第k节 有课
                                if(curCourseTable[i][j][k] != null){
                                    // 原本这一节就有课 冲突
                                    throw new ResponseException(
                                            "第" + (i + 1) + "周 星期" + (j + 1) + " 第" + (k + 1) + "节课程时间冲突",
                                            false
                                    );
                                } else{
                                    curCourseTable[i][j][k] = name;
                                }
                            }
                        }
                    }
                }
            }
        }
        return curCourseTable;
    }

    /**
     * 生成课表
     * @param week 新课开的周
     * @param weekday 新课开的星期几
     * @param section 新课开的第几节
     * @param name 课程名
     * @return 生成的课表 rst[i][j][k] 表示第i周星期j第k节是否有课, 有课为课名, 没课为null
     */
    public static String[][][] generateCourseTable(
            String week,
            String weekday,
            String section,
            String name
    ){
        return generateCourseTable(null, week, weekday, section, name);
    }

    /**
     * 生成课表
     * @return 生成的课表 rst[i][j][k] 表示第i周星期j第k节的课, 有课为课名, 没课为null
     */
    public static String[][][] generateCourseTable(
            List<Course> courseList
    ){
        String[][][] courseTable = null;
        for (Course course : courseList) {
            courseTable = generateCourseTable(courseTable, course.getWeek(), course.getWeekday(), course.getSection(), course.getName());
        }
        return courseTable;
    }
}
