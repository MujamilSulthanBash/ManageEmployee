package com.i2i.ems.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.i2i.ems.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.repository.EmployeeRepository;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = EmployeeMapper.mapEmployee(employeeDto);
            logger.info("Employee created with name {}", employee.getName());
            return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
        } catch (DuplicateKeyException duplicateKeyException) {
            logger.warn("Employee name {} Already present ", employeeDto.getName());
            throw new DuplicateKeyException("Employee name " + employeeDto.getName() + " Already present ");
        }
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        List<Employee> employees = employeeRepository.findByIsDeleteFalse();
        if (employees.isEmpty()) {
            logger.info("No Active Employee in DataBase");
            throw new NoSuchElementException("No Active Employee in DataBase");
        } else {
            for (Employee employee : employees) {
                employeesDto.add(EmployeeMapper.mapEmployeeDto(employee));
            }
        }
        return employeesDto;
    }

    @Override
    public EmployeeDto retrieveEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(id);
        if (employee == null) {
            logger.info("No Such Employee id : {}", id);
            throw new NoSuchElementException("No Such Employee id : " + id);
        } else {
            return EmployeeMapper.mapEmployeeDtoForProject(employee);
        }
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee checkEmployee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeDto.getId());
        if (checkEmployee == null) {
            logger.info("While updating there is no Such Employee id : {}", employeeDto.getId());
            throw new NoSuchElementException("No Such Employee id : " + employeeDto.getId());
        } else {
            Employee employee = EmployeeMapper.mapEmployee(employeeDto);
            logger.info("Employee updated with name {}", employee.getName());
            return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(id);
        if (employee == null) {
            logger.info("While deleting there is no Such Employee id : {}", +id);
            throw new NoSuchElementException("No Such Employee id : " + id);
        }
        employee.setDelete(true);
        logger.info("Employee deleted with name {}", employee.getName());
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto assignDepartment(Long employeeId, Long departmentId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        if (employee == null) {
            logger.info("While assigning department there is No Such Employee id : {}", employeeId);
            throw new NoSuchElementException("No Such Employee id : " + employeeId);
        } else {
            Department department = employeeRepository.findDepartmentByIdAndIsDeleteFalse(departmentId);
            if (department == null) {
                logger.info("No Such Department id : {}", departmentId);
                throw new NoSuchElementException("No Such Department id : " + departmentId);
            } else {
                employee.setDepartment(department);
                logger.info("Department Assigned for Employee {}", employee.getName());
                return EmployeeMapper.mapEmployeeDtoForProject(employeeRepository.save(employee));
            }
        }
    }

    @Override
    public EmployeeDto assignProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        if (employee == null) {
            logger.info("While assigning project there is No Such Employee id : {}", employeeId);
            throw new NoSuchElementException("No Such Employee id : " + employeeId);
        } else {
            Project project = employeeRepository.findProjectByIdAndIsDeleteFalse(projectId);
            if (project == null) {
                logger.info("No Such Project id : {}", projectId);
                throw new NoSuchElementException("No Such Project id : " + projectId);
            } else {
                employee.getProjects().add(project);
                employeeRepository.save(employee);
                logger.info("Project Assigned for Employee {}", employee.getName());
                return EmployeeMapper.mapEmployeeDtoForProject(employee);
            }
        }
    }

}
