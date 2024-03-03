package com.easywork.easywork.input;


import jakarta.validation.constraints.NotBlank;

public record EmployeeInput(@NotBlank(message = "Name is mandatory")
                            String firstName,
                            String lastName,
                            String hireDate,
                            @NotBlank(message = "email is mandatory")
                            String email,
                            String birthDate
                       ) {
}
