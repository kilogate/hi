package com.kilogate.hi.java.io.object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 对象输入输出流的用法
 *
 * @author kilogate
 * @create 2020/8/23 16:26
 **/
public class ObjectIOUsage {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee s = new Employee("S", 8000);
        Manager m1 = new Manager("M1", 50000, s);
        Manager m2 = new Manager("M2", 50000, s);

        Employee[] staff = new Employee[3];
        staff[0] = s;
        staff[1] = m1;
        staff[2] = m2;

        String name = "/tmp/staff.dat";

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(name))) {
            objectOutputStream.writeObject(staff);
        }

        Employee[] newStaff = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(name))) {
            newStaff = (Employee[]) objectInputStream.readObject();
        }

        System.out.println(Arrays.toString(newStaff));
    }

    private static class Employee implements Serializable {
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

    private static class Manager extends Employee {
        private Employee secretary;

        public Manager() {
        }

        public Manager(String name, int salary, Employee secretary) {
            super(name, salary);
            this.secretary = secretary;
        }

        public Employee getSecretary() {
            return secretary;
        }

        public void setSecretary(Employee secretary) {
            this.secretary = secretary;
        }

        @Override
        public String toString() {
            return "Manager{" +
                    "secretary=" + secretary +
                    "} " + super.toString();
        }
    }
}
