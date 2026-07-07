package org.example;

public class Customer {

    private String name;
    private Address address;

    public Customer(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {

        System.out.println("Customer Name : " + name);
        System.out.println("City          : " + address.getCity());

    }
}
