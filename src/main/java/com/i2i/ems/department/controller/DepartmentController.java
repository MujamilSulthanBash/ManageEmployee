package com.i2i.ems.department.controller;

import java.util.List;

import com.i2i.ems.employee.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.service.DepartmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("ems/api/v1/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(
                departmentService.saveDepartment(departmentDto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        List<DepartmentDto> departmentsDto = departmentService.retrieveDepartments();
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(
                departmentService.retrieveDepartmentById(id),
                HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(
                departmentService.updateDepartment(departmentDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employeebydepartment/{id}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.retrieveEmployeeByDepartment(id), HttpStatus.OK);
    }

}
