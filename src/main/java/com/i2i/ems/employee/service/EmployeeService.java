package com.i2i.ems.employee.service;

import java.util.List;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.model.Employee;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface EmployeeService {
    /**
     * Save Employee entity.
     *
     * @param employeeDto - employee details.
     * @return the saved employee.
     */
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    /**
     * retrieve set of Employees.
     *
     * @return list of employees.
     */
    List<EmployeeDto> retrieveEmployees();

    /**
     * retrieve Employee By id.
     *
     * @param id - employee id.
     * @return the saved employee.
     */
    EmployeeDto retrieveEmployeeById(Long id);

    /**
     * update Employee entity.
     *
     * @param employeeDto - employee details.
     * @return the updated employee.
     */
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    /**
     * delete Employee entity.
     *
     * @param id - employee details.
     */
    void deleteEmployee(Long id);

    EmployeeDto assignDepartment(Long employeeId, Long departmentId);

    EmployeeDto assignProject(Long employeeId, Long projectId);

    List<EmployeeDto> getEmployeeByDepartment(Long id);

    List<EmployeeDto> getEmployeeByProject(Long id);
}
