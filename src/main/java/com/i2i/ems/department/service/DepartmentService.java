package com.i2i.ems.department.service;

import java.util.List;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.model.Department;

/**
 * Service interface for Department entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface DepartmentService {

    /**
     * This method is responsible for Save Department entity.
     *
     * @param departmentDto - {@link DepartmentDto}
     * @return DepartmentDto - saved {@link DepartmentDto} details.
     */
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    /**
     * This method is responsible for retrieve List of Departments.
     *
     * @return List<DepartmentDto> - {@link DepartmentDto} details
     */
    List<DepartmentDto> retrieveDepartments();

    /**
     * This method is responsible for retrieve Department By id.
     *
     * @param id - departmentId.
     * @return - {@link DepartmentDto} details.
     */
    DepartmentDto retrieveDepartmentById(Long id);

    /**
     * This method is responsible for update Department entity.
     *
     * @param departmentDto - {@link DepartmentDto} details.
     * @return - updated {@link DepartmentDto} details.
     */
    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    /**
     * This method is responsible for delete Department entity.
     *
     * @param id - department id.
     */
    void deleteDepartment(Long id);
}
