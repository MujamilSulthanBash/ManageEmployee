package com.i2i.ems.employee.mapper;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.model.Employee;
import com.i2i.ems.utility.AgeCalculator;
import com.i2i.ems.utility.GetListOfProject;
import com.i2i.ems.utility.HideDetail;

public class EmployeeMapper {

    public static EmployeeDto mapEmployeeDto(Employee employee)
    {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phoneNumber(HideDetail.showDetails(employee.getPhoneNumber()))
                .email(HideDetail.showDetails(employee.getEmail()))
                .age(AgeCalculator.calculateAge(employee.getDateOfBirth()))
                .departmentName(employee.getDepartment() == null ?
                        "Not Assigned" : employee.getDepartment().getName())
                .build();
        return employeeDto;
    }

    public static EmployeeDto mapEmployeeDtoForProject(Employee employee) {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phoneNumber(HideDetail.showDetails(employee.getPhoneNumber()))
                .email(HideDetail.showDetails(employee.getEmail()))
                .age(AgeCalculator.calculateAge(employee.getDateOfBirth()))
                .departmentName(employee.getDepartment() == null ?
                        "Not Assigned" : employee.getDepartment().getName())
                .projectName(GetListOfProject.displayProjects(employee.getProjects()).isEmpty() ?
                        "Not Assigned" : GetListOfProject.displayProjects(employee.getProjects()))
                .build();
        return employeeDto;
    }

    public static Employee mapEmployee(EmployeeDto employeeDto) {
        Employee employee = Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .build();
        return employee;
    }

}
