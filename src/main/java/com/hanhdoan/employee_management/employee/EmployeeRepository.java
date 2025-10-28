package com.hanhdoan.employee_management.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(String name, String deptName);

    @Query("SELECT COALESCE(MAX(e.id), 0) FROM Employee e")
    Integer findMaxId();

    @Query("SELECT e.department.name, COUNT(e) FROM Employee e GROUP BY e.department.id")
    List<Object[]> countEmployeesByDepartment();
}
