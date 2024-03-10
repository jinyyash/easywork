package com.easywork.easywork.controller;

import com.easywork.easywork.exception.PayrollBadRequestException;
import com.easywork.easywork.exception.PayrollNotFoundException;
import com.easywork.easywork.input.EmployeeInput;
import com.easywork.easywork.input.util.InputUtil;
import com.easywork.easywork.model.Employee;
import com.easywork.easywork.sevice.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/management/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee>  one(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getById(id);
       if(employee.isPresent()) {
           return new ResponseEntity<>(employee.get(), HttpStatus.OK);
       }
       throw new PayrollNotFoundException(id, "Employee");
    }

    @GetMapping
    public List<Employee> all() {
        return employeeService.getAll();
    }

    @PostMapping
    public ResponseEntity<Employee> newEmployee(@RequestBody @Valid EmployeeInput input) {
        try {
            return new ResponseEntity<>(employeeService.saveEmployee(input), HttpStatus.CREATED);
        } catch (DateTimeParseException e) {
            throw new PayrollBadRequestException("Wrong datetime on request! date should be on yyyy-MM-dd format");
        }
    }
}
