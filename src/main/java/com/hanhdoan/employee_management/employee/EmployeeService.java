package com.hanhdoan.employee_management.employee;

import org.springframework.stereotype.Service;
import com.hanhdoan.employee_management.util.UtilityService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private int idCounter = 0;
    private final UtilityService utilityService;

    public EmployeeService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee addEmployee(Employee employee) {
        idCounter++;
        employee.setId(idCounter);
        employee.setCode(utilityService.generateEmployeeCode(idCounter));
        employee.setName(utilityService.formatName(employee.getName()));
        employees.put(idCounter, employee);
        return employee;
    }
}
