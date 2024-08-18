package com.i2i.ems.employee.service;

import com.i2i.ems.department.mapper.DepartmentMapperTest;
import com.i2i.ems.department.service.DepartmentServiceImpl;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.employee.repository.EmployeeRepository;
import com.i2i.ems.model.Department;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.mapper.ProjectMapperTest;
import com.i2i.ems.project.service.ProjectServiceImpl;
import com.i2i.ems.utility.ProjectRecord;
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

class EmployeeServiceImplTest {
    
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @Mock
    DepartmentServiceImpl departmentService;

    @Mock
    ProjectServiceImpl projectService;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeDto employeeDto;
    private List<Employee> employees;
    private Department department;
    private Project project;
    private Employee assignDepartment;
    private EmployeeDto assignDepartmentDto;
    private Employee assignProject;
    private EmployeeDto assignProjectDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = EmployeeMapperTest.getEmployee();
        employeeDto = EmployeeMapperTest.getEmployeeDto();
        employees = EmployeeMapperTest.getEmployees();
        department = DepartmentMapperTest.getDepartment();
        assignDepartment = EmployeeMapperTest.getAssignEmployee();
        assignDepartmentDto = EmployeeMapperTest.getAssignEmployeeDto();
        project = ProjectMapperTest.getProject();
        assignProject = EmployeeMapperTest.getAssignProject();
        assignProjectDto = EmployeeMapperTest.getAssignProjectDto();
    }

    @Test
    void testSaveEmployeeSuccess() {
        when(employeeRepository.existsByName(employeeDto.getName())).thenReturn(false);
        when(employeeRepository.existsByPhoneNumber(employeeDto.getPhoneNumber())).thenReturn(false);
        when(employeeMapper.mapEmployee(employeeDto)).thenReturn(employee);
        when(employeeMapper.mapEmployeeDto(employee)).thenReturn(employeeDto);
        when(employeeRepository.save(employee)).thenReturn(employee);
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        assertEquals("Mujamil", savedEmployeeDto.getName());
    }

    @Test
    void testSaveEmployeeFailure() {
        when(employeeRepository.existsByName(employeeDto.getName())).thenReturn(true);
        when(employeeRepository.existsByPhoneNumber(employeeDto.getPhoneNumber())).thenReturn(true);
        DuplicateKeyException duplicateKeyException = assertThrows(DuplicateKeyException.class, () -> {
            employeeService.saveEmployee(employeeDto); });
        assertEquals("Employee name Mujamil Already present ", duplicateKeyException.getMessage());
    }

    @Test
    void testRetrieveEmployeesSuccess() {
        when(employeeRepository.findByIsDeleteFalse()).thenReturn(employees);
        when(employeeMapper.mapEmployee(employeeDto)).thenReturn(employee);
        List<EmployeeDto> result = employeeService.retrieveEmployees();
        assertEquals(1, result.size());
    }

    @Test
    void testRetrieveEmployeesFailure() {
        when(employeeRepository.findByIsDeleteFalse()).thenReturn(new ArrayList<>());
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.retrieveEmployees(); });
        assertEquals("No Active Employee in DataBase", noSuchElementException.getMessage());
    }

    @Test
    void testRetrieveEmployeeByIdSuccess() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(employeeMapper.mapEmployeeDtoForProject(employee)).thenReturn(employeeDto);
        EmployeeDto result = employeeService.retrieveEmployeeById(1L);
        assertEquals("Mujamil", result.getName());
    }

    @Test
    void testRetrieveEmployeeByIdFailure() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.retrieveEmployeeById(1L); });
        assertEquals("No Such Employee id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testUpdateEmployeeSuccess() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(employeeMapper.mapEmployee(employeeDto)).thenReturn(employee);
        when(employeeMapper.mapEmployeeDto(employee)).thenReturn(employeeDto);
        when(employeeRepository.save(employee)).thenReturn(employee);
        EmployeeDto updateEmployee = employeeService.updateEmployee(employeeDto);
        assertEquals("Mujamil", updateEmployee.getName());
    }

    @Test
    void testUpdateEmployeeFailure() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.updateEmployee(employeeDto); });
        assertEquals("No Such Employee id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testDeleteEmployeeSuccess() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee checkEmployee = employeeRepository.save(employee);
        assertEquals("Mujamil", checkEmployee.getName());
    }

    @Test
    void testDeleteEmployeeFailure() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.deleteEmployee(1L); });
        assertEquals("No Such Employee id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testAssignDepartmentSuccess() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(departmentService.readDepartmentById(1L)).thenReturn(department);
        when(employeeMapper.mapEmployeeDto(assignDepartment)).thenReturn(assignDepartmentDto);
        when(employeeRepository.save(employee)).thenReturn(assignDepartment);
        EmployeeDto assignDepartment = employeeService.assignDepartment(1L, 1L);
        assertEquals("dev", assignDepartment.getDepartmentName());
    }

    @Test
    void testAssignDepartmentFailureEmployee() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(null);
        when(departmentService.readDepartmentById(1L)).thenReturn(department);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.assignDepartment(1L, 1L); });
        assertEquals("No Such Employee id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testAssignDepartmentFailureDepartment() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(departmentService.readDepartmentById(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.assignDepartment(1L, 1L); });
        assertEquals("No Such Department id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testAssignProjectSuccess() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(projectService.readProjectById(1L)).thenReturn(project);
        Project project1 = projectService.readProjectById(1L);
        when(employeeMapper.mapEmployeeDtoForProject(employeeRepository.save(employee))).thenReturn(assignProjectDto);
        EmployeeDto assignProject = employeeService.assignProject(1L, 1L);
        assertEquals(ProjectRecord.getListOfProjects(ProjectMapperTest.getProjects()), assignProject.getProjectNames());
    }

    @Test
    void testAssignProjectFailureEmployee() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(null);
        when(projectService.readProjectById(1L)).thenReturn(project);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.assignProject(1L, 1L); });
        assertEquals("No Such Employee id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testAssignProjectFailureProject() {
        when(employeeRepository.findEmployeeByIdAndIsDeleteFalse(1L)).thenReturn(employee);
        when(projectService.readProjectById(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            employeeService.assignProject(1L, 1L); });
        assertEquals("No Such Project id : 1", noSuchElementException.getMessage());
    }
}