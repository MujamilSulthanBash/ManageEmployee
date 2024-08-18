package com.i2i.ems.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.i2i.ems.department.service.DepartmentServiceImpl;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.repository.EmployeeRepository;
import com.i2i.ems.model.Department;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.service.ProjectServiceImpl;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private static Logger logger;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    ProjectServiceImpl projectService;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsByName(employeeDto.getName()) && employeeRepository.existsByPhoneNumber(employeeDto.getPhoneNumber())) {
            logger.warn("Employee name {} Already present ", employeeDto.getName());
            throw new DuplicateKeyException("Employee name " + employeeDto.getName() + " Already present ");
        }
        Employee employee = employeeMapper.mapEmployee(employeeDto);
        logger.info("Employee created with name {}", employee.getName());
        return employeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        List<Employee> employees = employeeRepository.findByIsDeleteFalse();
        if (employees.isEmpty()) {
            logger.info("No Active Employee in DataBase");
            throw new NoSuchElementException("No Active Employee in DataBase");
        }
        for (Employee employee : employees) {
            employeesDto.add(employeeMapper.mapEmployeeDto(employee));
        }
        return employeesDto;
    }

    @Override
    public EmployeeDto retrieveEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(id);
        if (employee == null) {
            logger.info("No Such Employee id : {}", id);
            throw new NoSuchElementException("No Such Employee id : " + id);
        }
        return employeeMapper.mapEmployeeDtoForProject(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee checkEmployee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeDto.getId());
        if (checkEmployee == null) {
            logger.info("While updating there is no Such Employee id : {}", employeeDto.getId());
            throw new NoSuchElementException("No Such Employee id : " + employeeDto.getId());
        }
        Employee employee = employeeMapper.mapEmployee(employeeDto);
        logger.info("Employee updated with name {}", employee.getName());
        return employeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public boolean deleteEmployee(Long id) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(id);
        if (employee == null) {
            logger.info("While deleting there is no Such Employee id : {}", +id);
            throw new NoSuchElementException("No Such Employee id : " + id);
        }
        employee.setDelete(true);
        logger.info("Employee deleted with name {}", employee.getName());
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public EmployeeDto assignDepartment(Long employeeId, Long departmentId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        if (null == employee) {
            logger.info("While assigning Department No Such Employee id : {}", employeeId);
            throw new NoSuchElementException("No Such Employee id : " + employeeId);
        }
        Department department = departmentService.readDepartmentById(departmentId);
        if (null == department) {
            logger.info("While assigning Department No Such Department id : {}", departmentId);
            throw new NoSuchElementException("No Such Department id : " + departmentId);
        }
        employee.setDepartment(department);
        logger.info("Department Assigned for Employee {}", employee.getName());
        return employeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto assignProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        if (employee == null) {
            logger.info("While assigning project there is No Such Employee id : {}", employeeId);
            throw new NoSuchElementException("No Such Employee id : " + employeeId);
        }
        Project project = projectService.readProjectById(projectId);
        if (project == null) {
            logger.info("No Such Project id : {}", projectId);
            throw new NoSuchElementException("No Such Project id : " + projectId);
        }
        if (null == employee.getProjects()) {
            List<Project> projects = new ArrayList<>();
            projects.add(project);
            employee.setProjects(projects);
        } else {
            employee.getProjects().add(project);
        }
        logger.info("Project Assigned for Employee {}", employee.getName());
        return employeeMapper.mapEmployeeDtoForProject(employeeRepository.save(employee));
    }

}
