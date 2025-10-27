package com.hanhdoan.employee_management.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.hanhdoan.employee_management.auth.AuthController;
import com.hanhdoan.employee_management.employee.EmployeeWebController;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = { EmployeeWebController.class, AuthController.class })
public class GlobalWebExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", "An unexpected error occurred: " + ex.getMessage());

        String uri = request.getRequestURI();
        mav.addObject("backUrl", uri);

        return mav;
    }
}
