package com.i2i.ems.employee.controller;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.service.DepartmentService;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.service.EmployeeService;
import com.i2i.ems.model.Department;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("ems/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity(
                EmployeeMapper.mapEmployeeDto(
                        employeeService.saveEmployee(
                                EmployeeMapper.mapEmployee(
                                        employeeDto
                                )
                        )
                ), HttpStatus.CREATED);
    }

    @PostMapping("/assigndepartment")
    public ResponseEntity<EmployeeDto> assignDepartment(@RequestBody EmployeeDto employeeDto, @RequestBody DepartmentDto departmentDto, @PathVariable Long employeeId, @PathVariable Long departmentId) {
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        Department department = departmentService.retrieveDepartmentById(departmentId);
        employee.setDepartment(department);
        EmployeeDto assignDepartment = EmployeeMapper.mapEmployeeDto(
                employeeService.saveEmployee(employee)
        );
        return new ResponseEntity(assignDepartment, HttpStatus.CREATED);
    }

    @PostMapping("/assignproject")
    public ResponseEntity<EmployeeDto> assignProject(@RequestBody EmployeeDto employeeDto, @RequestBody ProjectDto projectDto, @PathVariable Long employeeId, @PathVariable Long projectId) {
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        Project project = projectService.retrieveProjectById(projectId);
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        employee.setProjects(projects);
        return new ResponseEntity<>(
                EmployeeMapper.mapEmployeeDto(
                        employeeService.saveEmployee(employee)
                ), HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees = employeeService.retrieveEmployees();
        for (Employee employee : employees) {
            employeeDtos.add(EmployeeMapper.mapEmployeeDto(employee));
        }
        return new ResponseEntity(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity(
                EmployeeMapper.mapEmployeeDto(
                        employeeService.retrieveEmployeeById(id)
                ), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto updateEmployeeDto) {
        Employee employee = employeeService.retrieveEmployeeById(id);
        Employee updatedEmployee = EmployeeMapper.mapEmployee(updateEmployeeDto);
        updatedEmployee.setId(employee.getId());
        return new ResponseEntity(
                EmployeeMapper.mapEmployeeDto(
                        employeeService.updateEmployee(
                                updatedEmployee)
                ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.retrieveEmployeeById(id);
        employeeService.deleteEmployee(employee);
        return new ResponseEntity(HttpStatus.OK);
    }

}
