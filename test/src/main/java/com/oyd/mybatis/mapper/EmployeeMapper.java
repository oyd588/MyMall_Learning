package com.oyd.mybatis.mapper;

import com.oyd.mybatis.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {

    List<Employee> getAllEmp();

    Employee getEmpById(Integer eid);

    List<Employee> getEmpsByDeptId(Integer deptId);
}
