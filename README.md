# 学生选课管理系统

## 数据库

### 用户表 user

* 学号/教师号 uid int

* 姓名 name str

* 性别 sex str

* 年龄 age int

* 电话 tel str

* 邮箱 email str

* 角色 (老师/学生) role str

### 课程表 course

* 课程号 course_id int

* 开课老师 uid int

* 课程名 name str

* 容量 capacity int

* 备注 remarks str
  
  > 选课时展示的信息 一般情况下存选课时间(前端生成 | 后端生成?)

* 上课时间-周 week str(10)
  
  > 10位  为1表示开, 正常情况下开10周的课, 即11111 11111,  特殊课程如形势与政策开两周, 如 00100 00100 表示三, 八周开课

* 上课时间-星期几 weekday str(7)
  
  > 7位 如上

* 上课时间-第几节 section str(12)
  
  > 12位 如上

### 学生选课表 student_course

* 学生 uid int

* 选择的课程 course_id int

## 后端功能

* [x] 登录

* [x] 注册
  
  - [x] 需要验证码

* [x] 老师发表课程
  
  * 时间是否冲突
  
  * 时间 周 + 星期几

* [x] 老师扩课
- [x] 老师查看自己的课表

- [x] 查询课程
  
  * 课程号
  
  * 课名
  
  * 教师号
* [x] 学生选课
  
  * 选课是否满了 (谁手速快就是谁的)
  
  * 选课时间是否冲突

* [x] 学生退课
- [x] 学生查看自己的课表

### 前端页面