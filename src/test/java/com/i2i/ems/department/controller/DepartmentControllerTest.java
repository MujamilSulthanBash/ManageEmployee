package com.i2i.ems.department.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.mapper.DepartmentMapperTest;
import com.i2i.ems.department.service.DepartmentService;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DepartmentControllerTest {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;

    private DepartmentDto departmentDto;
    private List<DepartmentDto> departmentsDto;
    private List<EmployeeDto> employeesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentDto = DepartmentMapperTest.getDepartmentDto();
        departmentsDto = DepartmentMapperTest.getDepartmentsDto();
        employeesDto = EmployeeMapperTest.getEmployeesDto();
    }

    @Test
    void testCreateDepartment() {
        when(departmentService.saveDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> createDepartment = departmentController.createDepartment(departmentDto);
        assertEquals(HttpStatus.CREATED, createDepartment.getStatusCode());
    }

    @Test
    void testGetDepartments() {
        when(departmentService.retrieveDepartments()).thenReturn(departmentsDto);
        ResponseEntity<List<DepartmentDto>>  retrieveDepartments = departmentController.getDepartments();
        assertEquals(HttpStatus.OK, retrieveDepartments.getStatusCode());
    }

    @Test
    void testGetDepartmentById() {
        when(departmentService.retrieveDepartmentById(1L)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> retrieveDepartmentById = departmentController.getDepartmentById(1L);
        assertEquals(HttpStatus.OK, retrieveDepartmentById.getStatusCode());
    }

    @Test
    void testUpdateDepartment() {
        when(departmentService.updateDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> updatedDepartment = departmentController.updateDepartment(departmentDto);
        assertEquals(HttpStatus.OK, updatedDepartment.getStatusCode());
    }

    @Test
    void testDeleteDepartment() {
        when(departmentService.deleteDepartment(1L)).thenReturn(true);
        ResponseEntity<Boolean> deleteDepartment = departmentController.deleteDepartment(1L);
        assertEquals(HttpStatus.OK, deleteDepartment.getStatusCode());
    }

    @Test
    void getEmployeeByDepartment() {
        when(departmentService.retrieveEmployeeByDepartment(1L)).thenReturn(employeesDto);
        ResponseEntity<List<EmployeeDto>> retrieveEmployeeByDepartment = departmentController.getEmployeeByDepartment(1L);
        assertEquals(HttpStatus.OK, retrieveEmployeeByDepartment.getStatusCode());
    }

}