package com.i2i.ems.department.mapper;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.model.Department;

public class DepartmentMapper {
    public static DepartmentDto mapDepartmentDto(Department department) {
        DepartmentDto departmentDto = DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
        return  departmentDto;
    }

    public static Department mapDepartment(DepartmentDto departmentDto) {
        Department department = Department.builder()
                .name(departmentDto.getName())
                .build();
        return department;
    }
}
