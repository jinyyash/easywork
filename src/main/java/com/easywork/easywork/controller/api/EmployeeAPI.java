package com.easywork.easywork.controller.api;

import com.easywork.easywork.input.EmployeeInput;
import com.easywork.easywork.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Employee", description = "Employee API")
public interface EmployeeAPI {
    @Operation(
            summary = "Fetch Employee by ID",
            description = "fetches employee entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<Employee> one(@PathVariable Long id);

    @Operation(
            summary = "Fetch All Employee by ID",
            description = "fetches all employee entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public List<Employee> all();

    @Operation(
            summary = "Create new Employee",
            description = "Create a new Employee on system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    public ResponseEntity<Employee> newEmployee(@RequestBody @Valid EmployeeInput input);
}
