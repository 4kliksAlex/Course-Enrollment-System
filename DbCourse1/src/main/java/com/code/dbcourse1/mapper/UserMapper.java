package com.code.dbcourse1.mapper;

import com.code.dbcourse1.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper{

    @Select("select * from user")
    List<User> getAllUser();


    @Select("select * from user where name=#{name} and password=#{password} limit 1")
    User getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Insert("insert into user(name, password, role, sex, age, tel, email) values (#{name}, #{password}, #{role}, #{sex}, #{age}, #{tel}, #{email})")
    boolean register(String name, String password, String role, String sex, int age, String tel, String email);

    @Select("select * from user where uid=#{uid} and password=#{password} limit 1")
    User getUserByUIdAndPassword(long uid, String password);
}
