package com.i2i.ems.employee.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
}
