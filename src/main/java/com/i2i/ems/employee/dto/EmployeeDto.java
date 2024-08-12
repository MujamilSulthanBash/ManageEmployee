package com.i2i.ems.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private String age;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private String departmentName;
    private String projectName;
}
