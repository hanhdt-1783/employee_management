package com.hanhdoan.employee_management.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {
    private final EmployeeService employeeService;

    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getAllEmployees(@RequestParam(required = false) String search, Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees(search));
        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "employees/add";
        }
        employeeService.addEmployee(employeeDTO);
        return "redirect:/employees/list";
    }
}
