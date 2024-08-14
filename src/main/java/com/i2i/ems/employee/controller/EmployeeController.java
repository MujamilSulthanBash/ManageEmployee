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

@RestController()
@RequestMapping("ems/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employeesDto = employeeService.retrieveEmployees();
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.retrieveEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto updateEmployeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(updateEmployeeDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{employeeId}/departments/{departmentId}")
    public ResponseEntity<EmployeeDto> assignDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
        return new ResponseEntity<>(employeeService.assignDepartment(employeeId, departmentId), HttpStatus.CREATED);
    }

    @PutMapping("{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDto> assignProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        return new ResponseEntity<>(employeeService.assignProject(employeeId, projectId), HttpStatus.OK
        );
    }

}