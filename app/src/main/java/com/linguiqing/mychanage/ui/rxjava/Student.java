package com.linguiqing.mychanage.ui.rxjava;

/**
 * ***************************************
 * statement: 学生实体类
 * auther: lingguiqin
 * date created : 2017/7/9 0009
 * ***************************************
 */

public class Student {
    public Student(String name, String id, int age, String[] coueses) {
        this.name = name;
        this.id = id;
        this.courses = coueses;  // 课程
        this.age = age;
    }

    public String name;
    public String id;
    public String[] courses;
    public int age;
}
