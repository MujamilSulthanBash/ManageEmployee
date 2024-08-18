package com.i2i.ems.department.mapper;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.model.Department;
import com.i2i.ems.utility.EmployeeRecord;

/**
 * Utility class for mapping between Department and DepartmentDTO.
 *
 * <p>
 * This class provides static methods for converting between Department entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class DepartmentMapper {

    /**
     * Converts an {@link Department} entity to an {@link DepartmentDto}.
     * such as id, name.
     *
     * @param department {@link Department} The Employee entity to be converted.
     * @return {@link DepartmentDto} The corresponding Employee DTO.
     */
    public DepartmentDto mapDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .employeeNames(EmployeeRecord.getListOfEmployees(department.getEmployees()))
                .build();
    }

    /**
     * Converts an {@link DepartmentDto} to an {@link Department} entity.
     * such as name.
     *
     * @param departmentDto {@link DepartmentDto} The Employee DTO to be converted.
     * @return {@link Department} The corresponding Employee entity.
     */
    public Department mapDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .name(departmentDto.getName())
                .build();
    }

}
