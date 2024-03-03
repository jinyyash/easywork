package com.easywork.easywork.sevice;

import com.easywork.easywork.dao.EmployeeRepository;
import com.easywork.easywork.input.EmployeeInput;
import com.easywork.easywork.input.util.InputUtil;
import com.easywork.easywork.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getById(Long id){
        return employeeRepository.findById(id);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeeInput input)throws DateTimeParseException {
        Employee newEmployee = Employee.builder()
                .firstName(input.firstName())
                .lastName(input.lastName())
                .email(input.email())
                .build();

        if (InputUtil.isNullOrEmpty(input.birthDate())) {
            LocalDate birthDate = InputUtil.getDate(input.birthDate());
            newEmployee.setBirthDate(birthDate);
        }

        if(InputUtil.isNullOrEmpty(input.hireDate())){
            LocalDate hiredDate = InputUtil.getDate(input.hireDate());
            newEmployee.setHireDate(hiredDate);
        }

        return employeeRepository.save(newEmployee);
    }
}


