package com.easywork.easywork.controller;

import com.easywork.easywork.controller.api.EmployeeAPI;
import com.easywork.easywork.exception.PayrollBadRequestException;
import com.easywork.easywork.exception.PayrollNotFoundException;
import com.easywork.easywork.input.EmployeeInput;
import com.easywork.easywork.model.Employee;
import com.easywork.easywork.sevice.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Tag(name ="Employee", description ="Employee API")
@RestController
@RequestMapping("/api/v1/management/employees")
public class EmployeeController implements EmployeeAPI {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Fetch Employee by ID",
            description = "fetches employee entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> one(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getById(id);
       if(employee.isPresent()) {
           return new ResponseEntity<>(employee.get(), HttpStatus.OK);
       }
       throw new PayrollNotFoundException(id, "Employee");
    }

    @Operation(
            summary = "Fetch All Employee by ID",
            description = "fetches all employee entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public List<Employee> all() {
        return employeeService.getAll();
    }
    @Operation(
            summary = "Create new Employee",
            description = "Create a new Employee on system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping
    public ResponseEntity<Employee> newEmployee(@RequestBody @Valid EmployeeInput input) {
        try {
            return new ResponseEntity<>(employeeService.saveEmployee(input), HttpStatus.CREATED);
        } catch (DateTimeParseException e) {
            throw new PayrollBadRequestException("Wrong datetime on request! date should be on yyyy-MM-dd format");
        }
    }
}
