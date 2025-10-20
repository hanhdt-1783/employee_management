package com.hanhdoan.employee_management.employee;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.hanhdoan.employee_management.department.Department;
import com.hanhdoan.employee_management.department.DepartmentRepository;
import com.hanhdoan.employee_management.util.UtilityService;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final UtilityService utilityService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(UtilityService utilityService, EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.utilityService = utilityService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Employee> getAllEmployees(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return employeeRepository.findAll();
        }
        return employeeRepository.findByNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(keyword, keyword);
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) throws Exception {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Department dept = departmentRepository.findByName(employeeDTO.getDepartmentName());
        if (dept == null) {
            dept = new Department();
            dept.setName(employeeDTO.getDepartmentName());
        }
        employee.setDepartment(dept);

        Integer maxId = employeeRepository.findMaxId();
        int idCounter = (maxId != null ? maxId.intValue() : 0) + 1;
        employee.setCode(utilityService.generateEmployeeCode(idCounter));
        employee.setName(utilityService.formatName(employeeDTO.getName()));

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, EmployeeDTO employeeDTO) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new Exception("Employee with ID " + id + " not found.");
        }
        Employee existingEmployee = employee.get();

        Department dept = departmentRepository.findByName(employeeDTO.getDepartmentName());
        if (dept == null) {
            dept = new Department();
            dept.setName(employeeDTO.getDepartmentName());
        }
        existingEmployee.setDepartment(dept);

        Integer maxId = employeeRepository.findMaxId();
        int idCounter = (maxId != null ? maxId.intValue() : 0) + 1;
        existingEmployee.setCode(utilityService.generateEmployeeCode(idCounter));
        existingEmployee.setName(utilityService.formatName(employeeDTO.getName()));
        existingEmployee.setEmail(employeeDTO.getEmail());

        return employeeRepository.save(existingEmployee);
    }

    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
