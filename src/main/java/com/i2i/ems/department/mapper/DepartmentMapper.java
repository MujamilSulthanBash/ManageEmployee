package com.i2i.ems.department.mapper;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.model.Department;

public class DepartmentMapper {
    public static DepartmentDto mapDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public static Department mapDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .name(departmentDto.getName())
                .build();
    }
}
