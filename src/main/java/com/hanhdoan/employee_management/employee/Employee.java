package com.hanhdoan.employee_management.employee;

public class Employee {
    private int id;
    private String name;
    private String department;
    private String code;

    public Employee() {}

    public Employee(int id, String name, String department, String code) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.code = code;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
