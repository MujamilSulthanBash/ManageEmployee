package com.i2i.ems.employee.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.employee.service.EmployeeService;

class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeDto = EmployeeMapperTest.getEmployeeDto();
        employeesDto = EmployeeMapperTest.getEmployeesDto();
    }

    @Test
    void testCreateEmployee() {
        when(employeeService.saveEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> createEmployee = employeeController.createEmployee(employeeDto);
        assertEquals(HttpStatus.CREATED, createEmployee.getStatusCode());
    }

    @Test
    void testGetEmployees() {
        when(employeeService.retrieveEmployees()).thenReturn(employeesDto);
        ResponseEntity<List<EmployeeDto>>  retrieveEmployees = employeeController.getEmployees();
        assertEquals(HttpStatus.OK, retrieveEmployees.getStatusCode());
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.retrieveEmployeeById(1L)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> retrieveEmployeeById = employeeController.getEmployeeById(1L);
        assertEquals(HttpStatus.OK, retrieveEmployeeById.getStatusCode());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> updatedEmployee = employeeController.updateEmployee(employeeDto);
        assertEquals(HttpStatus.OK, updatedEmployee.getStatusCode());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeService.deleteEmployee(1L)).thenReturn(true);
        ResponseEntity<Boolean> deleteEmployee = employeeController.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, deleteEmployee.getStatusCode());
    }

    @Test
    void assignDepartment() {
        when(employeeService.assignDepartment(1L, 1L)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> assignDepartment = employeeController.assignDepartment(1L, 1L);
        assertEquals(HttpStatus.OK, assignDepartment.getStatusCode());
    }

    @Test
    void assignProject() {
        when(employeeService.assignProject(1L, 1L)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> assignProject = employeeController.assignProject(1L, 1L);
        assertEquals(HttpStatus.OK, assignProject.getStatusCode());
    }

}