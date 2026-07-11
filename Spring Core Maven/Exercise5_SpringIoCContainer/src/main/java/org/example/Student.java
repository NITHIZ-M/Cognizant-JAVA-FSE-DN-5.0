package com.example;


public class Student {


    private String name;

    private int rollNo;


    public void setName(String name) {
        this.name = name;
    }


    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }


    public void display(){

        System.out.println("Student Name : " + name);
        System.out.println("Roll Number : " + rollNo);

    }

}