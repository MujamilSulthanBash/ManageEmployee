package com.i2i.ems.department.service;

import com.i2i.ems.model.Department;

import java.util.List;

/**
 * Service interface for Department entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface DepartmentService {
    /**
     * Save Department entity.
     *
     * @param department - department details.
     * @return the saved department.
     */
    Department saveDepartment(Department department);

    /**
     * retrieve set of Departments.
     *
     * @return list of departments.
     */
    List<Department> retrieveDepartments();

    /**
     * retrieve Department By id.
     *
     * @param id - department id.
     * @return the saved department.
     */
    Department retrieveDepartmentById(Long id);

    /**
     * update Department entity.
     *
     * @param department - department details.
     * @return the updated department.
     */
    Department updateDepartment(Department department);

    /**
     * delete Department entity.
     *
     * @param department - department details.
     */
    void deleteDepartment(Department department);
}
