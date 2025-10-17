package com.hanhdoan.employee_management.employee;

import org.modelmapper.ModelMapper;

public class EmployeeController {
    private final ModelMapper modelMapper;

    public EmployeeController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
