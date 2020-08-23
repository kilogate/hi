package com.kilogate.hi.java.io.object;

import java.io.Serializable;

/**
 * Employee
 *
 * @author kilogate
 * @create 2020/8/23 16:24
 **/
public class Employee implements Serializable {
    String name;
    int salary;

    public Employee() {
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
