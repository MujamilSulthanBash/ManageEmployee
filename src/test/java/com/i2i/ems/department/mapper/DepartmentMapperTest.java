package com.i2i.ems.department.mapper;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.model.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentMapperTest {

    public static DepartmentDto getDepartmentDto() {
        return DepartmentDto.builder()
                .id(1L)
                .name("dev")
                .build();
    }

    public static Department getDepartment() {
        return Department.builder()
                .id(1L)
                .name("dev")
                .build();
    }

    public static Department getEmployeeByDepartment() {
        return Department.builder()
                .id(1L)
                .name("dev")
                .employees(EmployeeMapperTest.getEmployees())
                .build();
    }

    public static List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(getDepartment());
        return departments;
    }

    public static List<DepartmentDto> getDepartmentsDto() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        departmentsDto.add(getDepartmentDto());
        return departmentsDto;
    }
}
