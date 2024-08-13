package com.i2i.ems.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ems.department.mapper.DepartmentMapper;
import com.i2i.ems.department.service.DepartmentService;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.repository.EmployeeRepository;
import com.i2i.ems.model.Department;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.mapper.ProjectMapper;
import com.i2i.ems.project.service.ProjectService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapEmployee(employeeDto);
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        List<Employee> employees = employeeRepository.findByIsDeleteFalse();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.mapEmployeeDto(employee));
        }
        return employeesDto;
    }

    @Override
    public EmployeeDto retrieveEmployeeById(Long id) {
        return EmployeeMapper.mapEmployeeDtoForProject(employeeRepository.findEmployeeByIdAndIsDeleteFalse(id));
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapEmployee(employeeDto);
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(id);
        employee.setDelete(true);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto assignDepartment(Long employeeId, Long departmentId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        employee.setDepartment(DepartmentMapper.mapDepartment(departmentService.retrieveDepartmentById(departmentId)));
        return EmployeeMapper.mapEmployeeDtoForProject(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto assignProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        Project project = ProjectMapper.mapProject(projectService.retrieveProjectById(projectId));
        if (employee.getProjects() == null) {
            List<Project> projects = new ArrayList<>();
            projects.add(project);
            employee.setProjects(projects);
        } else {
            employee.getProjects().add(project);

        }
        employeeRepository.save(employee);
        return EmployeeMapper.mapEmployeeDtoForProject(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeeByDepartment(Long id) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        Department department = DepartmentMapper.mapDepartment(departmentService.retrieveDepartmentById(id));
        for (Employee employee : department.getEmployees()) {
            employeesDto.add(EmployeeMapper.mapEmployeeDtoForProject(employee));
        }
        return employeesDto;
    }

    @Override
    public List<EmployeeDto> getEmployeeByProject(Long id) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        Project project = ProjectMapper.mapProject(projectService.retrieveProjectById(id));
        for (Employee employee : project.getEmployees()) {
            employeesDto.add(EmployeeMapper.mapEmployeeDtoForProject(employee));
        }
        return employeesDto;
    }

}
