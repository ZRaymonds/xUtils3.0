package com.example.xutils.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by ming on 2018/09/14.
 */


@Table(name = "person")
public class PersonTable {

    @Column(name = "id",isId = true,autoGen = true)
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "salary")
    private String salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "PersonTable{" +
                "id=" + id +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
