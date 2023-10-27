drop database if exists `db_course_1`;
create database `db_course_1`;
use `db_course_1`;

create table `user`(
    uid int primary key auto_increment comment '学号/教师号',
    `name` varchar(64) comment '姓名',
    `password` varchar(64) comment  '密码',
    `role` varchar(8) comment '角色',
    sex varchar(8) comment '性别',
    age int comment '年龄',
    tel varchar(16) comment '电话',
    email varchar(64) comment '邮箱'
) engine InnoDB default charset utf8 comment '用户表' auto_increment 10000000;

insert into user(`name`, `password`, `sex`, `age`, `tel`, `email`, `role`)
values ('admin123', 'admin123', '男', 19, '15123597547', '3262954152@qq.com', 'teacher'),
       ('admin', 'admin', '男', 19, '15123597547', '3262954152@qq.com', 'student');

create table `course`(
    course_id int primary key auto_increment comment '课程号',
    uid int comment '开课老师',
    `name` varchar(64) comment '课程名',
    capacity int comment '容量',
    remarks varchar(64) comment '备注',
    `week` varchar(10) default '1111111111' comment '上课时间-周',
    weekday varchar(7) comment '上课时间-星期几',
    section varchar(12) comment '上课时间-第几节'
) engine InnoDB default charset utf8 comment '课程表' auto_increment 10000;

insert into course(uid, `name`, capacity, remarks, weekday, section)
values (10000000, 'C语言', 30, '周二,四 9,10,11,12节', '0101000', '000000001111'),
       (10000000, '数据库', 30, '周二,四 1,2节', '0101000', '110000000000'),
       (10000000, '数据结构', 30, '周二,四 3,4节', '0101000', '001100000000'),
       (10000000, '计算机网络', 30, '周二,四 5,6节', '0101000', '000011000000'),
       (10000000, '操作系统', 30, '周二,四 7,8节', '0101000', '000000110000'),
       (10000000, '数学', 30, '周一,三 1,2节', '1010000', '110000000000'),
       (10000000, '英语', 30, '周一,三 3,4节', '1010000', '001100000000'),
       (10000000, '物理', 30, '周一,三 5,6节', '1010000', '000011000000'),
       (10000000, '化学', 30, '周一,三 7,8节', '1010000', '000000110000'),
       (10000000, '地理', 30, '周一,三 9,10节', '1010000', '000000001100'),
       (10000000, '生物', 30, '周一,三 11,12节', '1010000', '000000000011')
;

insert into course(uid, `name`, capacity, remarks, weekday, section, week)
values  (10000000, '形势与政策', 30, '1,6周 周五 9,10节', '0000100', '000000001100', '1000010000'),
        (10000000, '形势与政策', 30, '2,7周 周五 9,10节', '0000100', '000000001100', '0100001000'),
        (10000000, '形势与政策', 30, '3,8周 周五 9,10节', '0000100', '000000001100', '0010000100'),
        (10000000, '形势与政策', 30, '4,9周 周五 9,10节', '0000100', '000000001100', '0001000010'),
        (10000000, '形势与政策', 30, '5,10周 周五 9,10节', '0000100', '000000001100', '0000100001')
;

create table student_course(
    uid int comment '学生',
    course_id int comment '选择的课程'
) engine InnoDB default charset utf8 comment '学生选课表';

insert into student_course values (10000001, 10000);