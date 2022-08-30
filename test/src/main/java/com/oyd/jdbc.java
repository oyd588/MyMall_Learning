package com.oyd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 复习使用jdbc连接数据库，执行CRUD
 */
public class jdbc {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //1.注册驱动
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获取连接
        Properties properties = new Properties();
//        properties.load(new FileInputStream("test\\src\\main\\resources/jdbc.properties"));
        properties.load(new FileInputStream("test/src/main/resources/jdbc.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
        //3.执行sql
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        //4.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
