package com.hanhdoan.employee_management.employee;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.hanhdoan.employee_management.department.Department;
import com.hanhdoan.employee_management.department.DepartmentRepository;
import com.hanhdoan.employee_management.exception.ResourceNotFoundException;
import com.hanhdoan.employee_management.util.UtilityService;
import java.util.List;

@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
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

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding new employee: {}", utilityService.toJson(employeeDTO));

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
        employeeRepository.save(employee);

        log.info("New employee added with ID: {}", employee.getId());

        return employee;
    }

    public Employee updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        log.info("Updating employee ID {}: {}", id, utilityService.toJson(employeeDTO));

        Employee employee = getById(id);

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
        employee.setEmail(employeeDTO.getEmail());
        employeeRepository.save(employee);

        log.info("Employee ID {} updated.", id);

        return employee;
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found."));
    }

    public void deleteEmployee(Integer id) {
        log.warn("Deleting employee with ID: {}", id);

        Employee employee = getById(id);
        employeeRepository.delete(employee);

        log.info("Employee with ID {} deleted.", id);
    }

    @Cacheable("employeeCount")
    public long getEmployeeCount() {
        log.info("Counting employees in DB...");
        return employeeRepository.count();
    }
}
