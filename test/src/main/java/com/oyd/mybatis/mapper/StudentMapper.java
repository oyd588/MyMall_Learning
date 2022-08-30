package com.oyd.mybatis.mapper;

import com.oyd.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> getStudents();

    Student getStudentById(Integer id);

    List<Student> getStudentByColumn(@Param("column") String column, @Param("value") String value);

    int insertStudent(Student student);

    Student getStudentByNameAndAge(@Param("name")String name,@Param("age")Integer age);
}
