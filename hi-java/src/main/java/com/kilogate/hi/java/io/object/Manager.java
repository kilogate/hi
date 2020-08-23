package com.kilogate.hi.java.io.object;

/**
 * Manager
 *
 * @author kilogate
 * @create 2020/8/23 16:24
 **/
public class Manager extends Employee {
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
