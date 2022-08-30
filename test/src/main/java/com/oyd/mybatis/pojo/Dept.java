package com.oyd.mybatis.pojo;

import java.util.List;

public class Dept {
    private Integer did;
    private String deptName;
    private List<Employee> emps;

    public Dept(Integer did) {
        this.did = did;
        System.out.println("Dept构造方法被调用");
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
        System.out.println("set id 调用");
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
        System.out.println("set deptName 调用");
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
        System.out.println("set emps 调用");
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", deptName='" + deptName + '\'' +
                ", emps=" + emps +
                '}';
    }
}
