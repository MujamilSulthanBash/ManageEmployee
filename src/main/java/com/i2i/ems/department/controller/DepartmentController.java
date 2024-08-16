package com.i2i.ems.department.controller;

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

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.service.DepartmentService;
import com.i2i.ems.employee.dto.EmployeeDto;

/**
 * REST controller for managing Department-related operations.
 *
 * <p>
 * This controller handles HTTP requests and provides endpoints for
 * creating, retrieving, updating, and deleting Department entities. The
 * controller maps client requests to the appropriate service methods
 * and returns responses in the form of JSON or other supported media types.
 * It is annotated with Spring MVC annotations to define the URL mappings
 * and request handling logic.
 * All responses are returned in a standardized format to ensure consistency across
 * the API.
 * </p>
 */
@RestController()
@RequestMapping("ems/api/v1/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * Creates a new department.
     *
     * @param departmentDto {@link DepartmentDto} The DTO containing department data.
     * @return The created {@link DepartmentDto} with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all departments.
     *
     * @return the list of all departments as {@link DepartmentDto} with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        List<DepartmentDto> departmentsDto = departmentService.retrieveDepartments();
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    /**
     * Retrieves a department by their unique department ID.
     *
     * @param id the unique department ID
     * @return the {@link DepartmentDto} if found with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.retrieveDepartmentById(id), HttpStatus.OK);
    }

    /**
     * Updates an existing department's details.
     *
     * @param updateDepartmentDto - {@link DepartmentDto} details.
     * @return the updated {@link DepartmentDto} with HTTP status 200 OK.
     */
    @PutMapping()
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto updateDepartmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment(updateDepartmentDto), HttpStatus.OK);
    }

    /**
     * Deletes an department by their unique department ID.
     *
     * @param id the unique department ID
     * @return HTTP status 200 OK.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves a list of all employees based on department id.
     *
     * @return the list of all Employee as {@link EmployeeDto} with HTTP status 200 OK.
     */
    @GetMapping("{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.retrieveEmployeeByDepartment(id), HttpStatus.OK);
    }

}
