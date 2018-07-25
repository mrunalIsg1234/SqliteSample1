package com.sqlitesample1;

/**
 * Created by mrunal3006 on 24-Jul-18.
 */

public class EmployeeModel {

    public String name;
    public String designation;
    public String age;
    public String joindate;

    public EmployeeModel(String name, String designation, String age, String joindate) {
        this.name = name;
        this.designation = designation;
        this.age = age;
        this.joindate = joindate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }
}
