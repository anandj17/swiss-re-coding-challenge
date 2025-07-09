package com.bigcompany.model;

public class Employee {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String managerId;
    private final double salary;

    public Employee(String id, String firstName, String lastName, double salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = (managerId == null || managerId.trim().isEmpty()) ? null : managerId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getManagerId() {
        return managerId;
    }

    public double getSalary() {
        return salary;
    }

}
