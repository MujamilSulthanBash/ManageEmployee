package com.i2i.ems.employee.mapper;

import com.i2i.ems.department.mapper.DepartmentMapperTest;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.model.Employee;
import com.i2i.ems.project.mapper.ProjectMapperTest;
import com.i2i.ems.utility.ProjectRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapperTest {

    public static Employee getEmployee() {
        return Employee.builder()
                .id(1L)
                .name("Mujamil")
                .email("mujamil@gmail.com")
                .phoneNumber("8056081438")
                .dateOfBirth(LocalDate.ofEpochDay(2001-06-29))
                .build();
    }

    public static Employee getAssignEmployee() {
        return Employee.builder()
                .id(1L)
                .name("Mujamil")
                .email("mujamil@gmail.com")
                .phoneNumber("8056081438")
                .dateOfBirth(LocalDate.ofEpochDay(2001-06-29))
                .department(DepartmentMapperTest.getDepartment())
                .build();
    }

    public static Employee getAssignProject() {
        return Employee.builder()
                .id(1L)
                .name("Mujamil")
                .email("mujamil@gmail.com")
                .phoneNumber("8056081438")
                .dateOfBirth(LocalDate.ofEpochDay(2001-06-29))
                .department(DepartmentMapperTest.getDepartment())
                .projects(ProjectMapperTest.getProjects())
                .build();
    }

    public static EmployeeDto getEmployeeDto() {
        return EmployeeDto.builder()
                .id(1L)
                .name("Mujamil")
                .email("mujamil@gmail.com")
                .phoneNumber("8056081438")
                .age("21Y 2M")
                .build();
    }

    public static EmployeeDto getAssignEmployeeDto() {
        return EmployeeDto.builder()
                .id(1L)
                .name("Mujamil")
                .email("mujamil@gmail.com")
                .phoneNumber("8056081438")
                .dateOfBirth(LocalDate.ofEpochDay(2001-06-29))
                .departmentName(DepartmentMapperTest.getDepartment().getName())
                .build();
    }

    public static EmployeeDto getAssignProjectDto() {
        return EmployeeDto.builder()
                .id(1L)
                .name("Mujamil")
                .email("mujamil@gmail.com")
                .phoneNumber("8056081438")
                .dateOfBirth(LocalDate.ofEpochDay(2001-06-29))
                .departmentName(DepartmentMapperTest.getDepartment().getName())
                .projectNames(ProjectRecord.getListOfProjects(ProjectMapperTest.getProjects()))
                .build();
    }

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(getEmployee());
        return employees;
    }

    public static List<EmployeeDto> getEmployeesDto() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employeesDto.add(getEmployeeDto());
        return employeesDto;
    }
}
