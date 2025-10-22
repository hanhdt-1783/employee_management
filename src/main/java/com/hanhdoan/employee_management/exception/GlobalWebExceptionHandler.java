package com.hanhdoan.employee_management.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.hanhdoan.employee_management.employee.EmployeeWebController;

@ControllerAdvice(assignableTypes = EmployeeWebController.class)
public class GlobalWebExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", "An unexpected error occurred: " + ex.getMessage());
        return mav;
    }
}
