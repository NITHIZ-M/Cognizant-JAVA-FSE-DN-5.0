package org.example;

public class Employee {

    private int id;
    private String name;
    private Department department;


    // Constructor Injection
    public Employee(Department department) {

        this.department = department;

    }


    // Setter Injection
    public void setId(int id) {

        this.id = id;

    }


    public void setName(String name) {

        this.name = name;

    }


    public void display() {

        System.out.println("Employee ID   : " + id);
        System.out.println("Employee Name : " + name);
        System.out.println("Department    : " + department.getDeptName());

    }

}
