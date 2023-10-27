package com.code.dbcourse1.pojo;


public class Course {

  private long courseId;
  private long uid;
  private String name;
  private long capacity;
  private String remarks;
  private String week;
  private String weekday;
  private String section;
  private long selectedNum;


  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getCapacity() {
    return capacity;
  }

  public void setCapacity(long capacity) {
    this.capacity = capacity;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }


  public String getWeekday() {
    return weekday;
  }

  public void setWeekday(String weekday) {
    this.weekday = weekday;
  }


  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public long getSelectedNum() {
    return selectedNum;
  }

  public void setSelectedNum(long selectedNum) {
    this.selectedNum = selectedNum;
  }
}
