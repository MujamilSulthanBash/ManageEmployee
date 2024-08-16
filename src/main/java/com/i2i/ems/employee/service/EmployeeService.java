package com.i2i.ems.employee.service;

import java.util.List;

import com.i2i.ems.employee.dto.EmployeeDto;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface EmployeeService {

    /**
     * This method is responsible for Save Employee entity.
     *
     * @param employeeDto - {@link EmployeeDto}
     * @return EmployeeDto - saved {@link EmployeeDto} details.
     */
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    /**
     * This method is responsible for retrieve List of Employees.
     *
     * @return List<EmployeeDto> - {@link EmployeeDto} details
     */
    List<EmployeeDto> retrieveEmployees();

    /**
     * This method is responsible for retrieve Employee By id.
     *
     * @param id - employee id.
     * @return - {@link EmployeeDto} details.
     */
    EmployeeDto retrieveEmployeeById(Long id);

    /**
     * This method is responsible for update Employee entity.
     *
     * @param employeeDto - {@link EmployeeDto} details.
     * @return - updated {@link EmployeeDto} details.
     */
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    /**
     * This method is responsible for delete Employee entity.
     *
     * @param id - employee id.
     */
    void deleteEmployee(Long id);

    /**
     * This method is responsible for assign department for employee
     *
     * @param employeeId   - employeeId.
     * @param departmentId - departmentId.
     * @return EmployeeDto - {@link EmployeeDto} details.
     */
    EmployeeDto assignDepartment(Long employeeId, Long departmentId);

    /**
     * This method is responsible for assign project for employee
     *
     * @param employeeId   - employeeId.
     * @param projectId - projectId.
     * @return EmployeeDto - {@link EmployeeDto} details.
     */
    EmployeeDto assignProject(Long employeeId, Long projectId);

}
