package com.i2i.ems.employee.service;

import com.i2i.ems.employee.repository.EmployeeRepository;
import com.i2i.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAllEmployee();
    }

    @Override
    public Employee retrieveEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(id);
        return employee.orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employee.setDelete(true);
        employeeRepository.save(employee);
    }
}
