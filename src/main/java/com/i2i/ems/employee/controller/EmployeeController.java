package com.i2i.ems.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.service.EmployeeService;

/**
 * REST controller for managing Employee-related operations.
 *
 * <p>
 * This controller handles HTTP requests and provides endpoints for
 * creating, retrieving, updating, and deleting Employee entities. The
 * controller maps client requests to the appropriate service methods
 * and returns responses in the form of JSON or other supported media types.
 * It is annotated with Spring MVC annotations to define the URL mappings
 * and request handling logic.
 * All responses are returned in a standardized format to ensure consistency across
 * the API.
 * </p>
 */
@RestController()
@RequestMapping("ems/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Creates a new employee.
     *
     * @param employeeDto {@link EmployeeDto} The DTO containing employee data.
     * @return The created {@link EmployeeDto} with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return the list of all employees as {@link EmployeeDto} with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employeesDto = employeeService.retrieveEmployees();
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    /**
     * Retrieves an employee by their unique employee ID.
     *
     * @param id the unique employee ID
     * @return the {@link EmployeeDto} if found with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.retrieveEmployeeById(id), HttpStatus.OK);
    }

    /**
     * Updates an existing employee's details.
     *
     * @param updateEmployeeDto - {@link EmployeeDto} details.
     * @return the updated {@link EmployeeDto} with HTTP status 200 OK.
     */
    @PutMapping()
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto updateEmployeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(updateEmployeeDto), HttpStatus.OK);
    }

    /**
     * Deletes an employee by their unique employee ID.
     *
     * @param id the unique employee ID
     * @return HTTP status 200 OK.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Assigning department for existing employee
     *
     * @param employeeId - employeeId
     * @param departmentId - departmentId
     * @return the updated {@link EmployeeDto} with HTTP status 201 CREATED.
     */
    @PutMapping("{employeeId}/departments/{departmentId}")
    public ResponseEntity<EmployeeDto> assignDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
        return new ResponseEntity<>(employeeService.assignDepartment(employeeId, departmentId), HttpStatus.CREATED);
    }

    /**
     * Assigning project for existing employee
     *
     * @param employeeId - employeeId
     * @param projectId - projectId
     * @return the updated {@link EmployeeDto} with HTTP status 201 CREATED.
     */
    @PutMapping("{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDto> assignProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        return new ResponseEntity<>(employeeService.assignProject(employeeId, projectId), HttpStatus.OK
        );
    }

}