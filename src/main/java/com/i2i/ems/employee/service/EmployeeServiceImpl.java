package com.i2i.ems.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.repository.EmployeeRepository;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        employee.setDepartment(employeeRepository.findDepartmentByIdAndIsDeleteFalse(departmentId));
        return EmployeeMapper.mapEmployeeDtoForProject(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto assignProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findEmployeeByIdAndIsDeleteFalse(employeeId);
        Project project = employeeRepository.findProjectByIdAndIsDeleteFalse(projectId);
        employee.getProjects().add(project);
        employeeRepository.save(employee);
        return EmployeeMapper.mapEmployeeDtoForProject(employee);
    }

}
