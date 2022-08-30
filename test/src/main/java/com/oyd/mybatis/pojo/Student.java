package com.oyd.mybatis.pojo;

import org.apache.ibatis.type.Alias;

//@Alias("stu")
public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public Integer getid() {
        return id;
    }

    public void setStuid(Integer id) {
        this.id = id;
    }

    public String getName() {
        System.out.println("getName方法被调用");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        System.out.println("getEmail方法被调用");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        System.out.println("getAge方法被调用");
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
