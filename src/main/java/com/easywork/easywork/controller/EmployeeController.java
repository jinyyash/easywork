package com.easywork.easywork.controller;

import com.easywork.easywork.exception.PayrollBadRequestException;
import com.easywork.easywork.exception.PayrollNotFoundException;
import com.easywork.easywork.input.EmployeeInput;
import com.easywork.easywork.input.util.InputUtil;
import com.easywork.easywork.model.Employee;
import com.easywork.easywork.sevice.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/management/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    Employee one(@PathVariable Long id) {
        return employeeService.getById(id)
                .orElseThrow(() -> new PayrollNotFoundException(id, "Employee"));
    }

    @GetMapping
    public List<Employee> all() {
        return employeeService.getAll();
    }

    @PostMapping
    public Employee newEmployee(@RequestBody @Valid EmployeeInput input) {
        try {
            return employeeService.saveEmployee(input);
        } catch (DateTimeParseException e) {
            throw new PayrollBadRequestException("Wrong datetime on request! date should be on yyyy-MM-dd format");
        }
    }
}
