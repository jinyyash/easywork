package com.easywork.easywork.controller;

import com.easywork.easywork.sevice.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindEmployee() throws Exception {
        var employeeMock = com.easywork.easywork.model.Employee.builder()
                .firstName("first")
                .lastName("last")
                .email("test@gmil.com")
                .birthDate(LocalDate.of(1990, 8, 24))
                .hireDate(LocalDate.of(2024, 1, 24))
                .build();

        Mockito.when(employeeService.getById(Mockito.anyLong())).thenReturn(Optional.ofNullable(employeeMock));

        mockMvc.perform(get("/api/v1/management/employees/1")).andExpectAll(
                status().isOk(),
                content().json(objectMapper.writeValueAsString(employeeMock))
        );
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        var employeeMock = com.easywork.easywork.model.Employee.builder()
                .firstName("first")
                .lastName("last")
                .email("test@gmil.com")
                .birthDate(LocalDate.of(1990, 8, 24))
                .hireDate(LocalDate.of(2024, 1, 24))
                .build();

        Mockito.when(employeeService.getById(Mockito.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/management/employees/1")).andExpect(
                status().isNotFound());
    }
}
