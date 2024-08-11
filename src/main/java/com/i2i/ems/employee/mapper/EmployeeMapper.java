package com.i2i.ems.employee.mapper;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.model.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
        return  employeeDto;
    }

    public static Employee mapEmployee(EmployeeDto employeeDto) {
        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .build();
        return employee;
    }
}
