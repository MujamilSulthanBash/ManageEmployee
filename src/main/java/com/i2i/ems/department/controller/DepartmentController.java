package com.i2i.ems.department.controller;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.mapper.DepartmentMapper;
import com.i2i.ems.department.service.DepartmentService;
import com.i2i.ems.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("ems/api/v1/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity(
                DepartmentMapper.mapDepartmentDto(
                        departmentService.saveDepartment(
                                DepartmentMapper.mapDepartment(
                                        departmentDto
                                )
                        )
                ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        List<DepartmentDto> employeeDtos = new ArrayList<>();
        List<Department> departments = departmentService.retrieveDepartments();
        for (Department department : departments) {
            employeeDtos.add(DepartmentMapper.mapDepartmentDto(department));
        }
        return new ResponseEntity(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity(
                DepartmentMapper.mapDepartmentDto(
                        departmentService.retrieveDepartmentById(id)
                ), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto updateDepartmentDto) {
        Department department = departmentService.retrieveDepartmentById(id);
        Department updatedDepaartment = DepartmentMapper.mapDepartment(updateDepartmentDto);
        updatedDepaartment.setId(department.getId());
        return new ResponseEntity(
                DepartmentMapper.mapDepartmentDto(
                        departmentService.updateDepartment(
                                updatedDepaartment)
                ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Department department = departmentService.retrieveDepartmentById(id);
        departmentService.deleteDepartment(department);
        return new ResponseEntity(HttpStatus.OK);
    }
}
