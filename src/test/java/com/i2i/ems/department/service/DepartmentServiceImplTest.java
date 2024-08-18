package com.i2i.ems.department.service;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.mapper.DepartmentMapper;
import com.i2i.ems.department.mapper.DepartmentMapperTest;
import com.i2i.ems.department.repository.DepartmentRepository;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.model.Department;
import com.i2i.ems.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DepartmentServiceImplTest {

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    DepartmentMapper departmentMapper;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    private Department department;
    private Department getEmployeeByDepartment;
    private DepartmentDto departmentDto;
    private List<Department> departments;
    private Employee employee;
    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = DepartmentMapperTest.getDepartment();
        departmentDto = DepartmentMapperTest.getDepartmentDto();
        departments = DepartmentMapperTest.getDepartments();
        employee = EmployeeMapperTest.getEmployee();
        employeeDto =EmployeeMapperTest.getEmployeeDto();
        employeesDto = EmployeeMapperTest.getEmployeesDto();
        getEmployeeByDepartment = DepartmentMapperTest.getEmployeeByDepartment();
    }

    @Test
    void testSaveDepartmentSuccess() {
        when(departmentRepository.existsByName(departmentDto.getName())).thenReturn(false);
        when(departmentMapper.mapDepartment(departmentDto)).thenReturn(department);
        when(departmentMapper.mapDepartmentDto(department)).thenReturn(departmentDto);
        when(departmentRepository.save(department)).thenReturn(department);
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        assertEquals("dev", savedDepartmentDto.getName());
    }

    @Test
    void testSaveDepartmentFailure() {
        when(departmentRepository.existsByName(departmentDto.getName())).thenReturn(true);
        DuplicateKeyException duplicateKeyException = assertThrows(DuplicateKeyException.class, () -> {
            departmentService.saveDepartment(departmentDto);
        });
        assertEquals("Department name dev Already present ", duplicateKeyException.getMessage());
    }

    @Test
    void testRetrieveDepartmentsSuccess() {
        when(departmentRepository.findByIsDeleteFalse()).thenReturn(departments);
        when(departmentMapper.mapDepartment(departmentDto)).thenReturn(department);
        List<DepartmentDto> result = departmentService.retrieveDepartments();
        assertEquals(1, result.size());
    }

    @Test
    void testRetrieveDepartmentsFailure() {
        when(departmentRepository.findByIsDeleteFalse()).thenReturn(new ArrayList<>());
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            departmentService.retrieveDepartments();
        });
        assertEquals("No Active Department in DataBase", noSuchElementException.getMessage());
    }

    @Test
    void testRetrieveDepartmentByIdSuccess() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(department);
        when(departmentMapper.mapDepartmentDto(department)).thenReturn(departmentDto);
        DepartmentDto result = departmentService.retrieveDepartmentById(1L);
        assertEquals("dev", result.getName());
    }

    @Test
    void testReadDepartmentByIdSuccess() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(department);
        Department result = departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L);
        assertEquals("dev", result.getName());
    }

    @Test
    void testReadDepartmentByIdFailure() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            departmentService.readDepartmentById(1L);
        });
        assertEquals("No Such Department id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testRetrieveDepartmentByIdFailure() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            departmentService.retrieveDepartmentById(1L);
        });
        assertEquals("No Such Department id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testUpdateDepartmentSuccess() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(department);
        when(departmentMapper.mapDepartment(departmentDto)).thenReturn(department);
        when(departmentMapper.mapDepartmentDto(department)).thenReturn(departmentDto);
        when(departmentRepository.save(department)).thenReturn(department);
        DepartmentDto updateDepartment = departmentService.updateDepartment(departmentDto);
        assertEquals("dev", updateDepartment.getName());
    }

    @Test
    void testUpdateDepartmentFailure() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            departmentService.updateDepartment(departmentDto);
        });
        assertEquals("No Such Department id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testDeleteDepartmentSuccess() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);
        Department checkDepartment = departmentRepository.save(department);
        assertEquals("dev", checkDepartment.getName());
    }

    @Test
    void testDeleteDepartmentFailure() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            departmentService.deleteDepartment(1L);
        });
        assertEquals("No Such Department id : 1", noSuchElementException.getMessage());
    }

    @Test
    public void testRetrieveEmployeeByDepartmentSuccess() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(getEmployeeByDepartment);
        when(employeeMapper.mapEmployeeDto(employee)).thenReturn(employeeDto);
        List<EmployeeDto> result = departmentService.retrieveEmployeeByDepartment(1L);
        assertEquals(employeesDto.size(), result.size());
    }

    @Test
    public void testRetrieveEmployeeByDepartmentFailure() {
        when(departmentRepository.findDepartmentByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            departmentService.retrieveDepartmentById(1L);
        });
        assertEquals("No Such Department id : 1", noSuchElementException.getMessage());
    }

}