package com.oyd;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.oyd.mybatis.mapper.DeptMapper;
import com.oyd.mybatis.mapper.EmployeeMapper;
import com.oyd.mybatis.mapper.StudentMapper;
import com.oyd.mybatis.pojo.Dept;
import com.oyd.mybatis.pojo.Employee;
import com.oyd.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class testMybatis {
    @Test
    public void start() throws IOException {
        //1.mybatis 主配置文件
        String config = "MybatisConfig.xml";

        //2.读取配置文件
        InputStream in = Resources.getResourceAsStream(config);

        //3.创建 SqlSessionFactory 对象,目的是获取 SqlSession
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);


        //4.获取 SqlSession,SqlSession 能执行 sql 语句
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        //5.执行 SqlSession 的 selectList()
//        List<Student> studentList = session.selectList("com.oyd.mybatis.mapper.StudentMapper.getStudents");
        List<Student> studentList = mapper.getStudents();


        //6.循环输出查询结果
        studentList.forEach( student -> System.out.println(student));

        //7.关闭 SqlSession，释放资源
        session.close();

    }
    @Test
    public void testProperties() throws IOException {
        String config = "MybatisConfig.xml";
        InputStream in = Resources.getResourceAsStream(config);

        Properties properties = new Properties();
        properties.load(Resources.getResourceAsStream("password.properties"));
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in,properties);


        //4.获取 SqlSession,SqlSession 能执行 sql 语句
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        //5.执行 SqlSession 的 selectList()
//        List<Student> studentList = session.selectList("com.oyd.mybatis.mapper.StudentMapper.getStudents");
        List<Student> studentList = mapper.getStudents();

        //6.循环输出查询结果
        studentList.forEach( student -> System.out.println(student));

        //7.关闭 SqlSession，释放资源
        session.close();

    }
    @Test
    public void testInsert() throws IOException {
        String config = "MybatisConfig.xml";
        InputStream in = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setName("李瑾素");
        student.setEmail("123@qq.com");
        student.setAge(18);

        System.out.println("插入前：" + student);
        mapper.insertStudent(student);

        System.out.println("插入后:" + student);
//        Student studentById = mapper.getStudentById(2);
//        System.out.println(studentById);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert1() throws IOException {
        String config = "MybatisConfig.xml";
        InputStream in = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);


        Student student = mapper.getStudentByNameAndAge(null, 20);
        System.out.println(student);

        sqlSession.close();
    }

    @Test
    public void testEmp1() throws IOException {
        String config = "MybatisConfig.xml";
        InputStream in = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

//        List<Employee> allEmp = mapper.getAllEmp();
//        allEmp.forEach(employee -> System.out.println(employee));

        Dept deptById = mapper.getDeptById(2);
//        List<Employee> emps = deptById.getEmps();
       deptById.getDeptName();
        System.out.println(deptById);
        sqlSession.close();
    }
}
