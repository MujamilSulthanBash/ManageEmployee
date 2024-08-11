package com.i2i.ems.employee.service;

import java.util.List;

import com.i2i.ems.model.Employee;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface EmployeeService {
    /**
     * Save Employee entity.
     *
     * @param employee - employee details.
     * @return the saved employee.
     */
    Employee saveEmployee(Employee employee);

    /**
     * retrieve set of Employees.
     *
     * @return list of employees.
     */
    List<Employee> retrieveEmployees();

    /**
     * retrieve Employee By id.
     *
     * @param id - employee id.
     * @return the saved employee.
     */
    Employee retrieveEmployeeById(Long id);

    /**
     * update Employee entity.
     *
     * @param employee - employee details.
     * @return the updated employee.
     */
    Employee updateEmployee(Employee employee);

    /**
     * delete Employee entity.
     *
     * @param employee - employee details.
     */
    void deleteEmployee(Employee employee);
}
