package com.i2i.ems.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.mapper.DepartmentMapper;
import com.i2i.ems.department.repository.DepartmentRepository;
import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.model.Department;
import com.i2i.ems.model.Employee;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private static Logger logger;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByName(departmentDto.getName())) {
            logger.warn("Department name {} Already present ", departmentDto.getName());
            throw new DuplicateKeyException("Department name " + departmentDto.getName() + " Already present ");
        }
        Department department = departmentMapper.mapDepartment(departmentDto);
        logger.info("Department created with name {}", department.getName());
        return departmentMapper.mapDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findByIsDeleteFalse();
        if (departments.isEmpty()) {
            logger.warn("No Active Department in DataBase");
            throw new NoSuchElementException("No Active Department in DataBase");
        }
        for (Department department : departments) {
            departmentsDto.add(departmentMapper.mapDepartmentDto(department));
        }
        return departmentsDto;
    }

    @Override
    public DepartmentDto retrieveDepartmentById(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        if (department == null) {
            logger.warn("while retrieving there is no Such Department id : {}", id);
            throw new NoSuchElementException("No Such Department id : " + id);
        }
        return departmentMapper.mapDepartmentDto(department);
    }

    @Override
    public Department readDepartmentById(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        if (department == null) {
            logger.warn("while readDepartmentById there is no Such Department id : {}", id);
            throw new NoSuchElementException("No Such Department id : " + id);
        }
        return department;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department departmentCheck = departmentRepository.findDepartmentByIdAndIsDeleteFalse(departmentDto.getId());
        if (departmentCheck == null) {
            logger.info("No Such Department id : {}", departmentDto.getId());
            throw new NoSuchElementException("No Such Department id : " + departmentDto.getId());
        }
        Department department = departmentMapper.mapDepartment(departmentDto);
        logger.info("Department updated with name {}", department.getName());
        return departmentMapper.mapDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public boolean deleteDepartment(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        if (department == null) {
            logger.info("While deleting there is no Such Department id : {}", id);
            throw new NoSuchElementException("No Such Department id : " + id);
        }
        department.setDelete(true);
        logger.info("Department deleted with name {}", department.getName());
        departmentRepository.save(department);
        return true;
    }

    @Override
    public List<EmployeeDto> retrieveEmployeeByDepartment(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        if (department == null) {
            logger.info("No such Department id {}", id);
            throw new NoSuchElementException("No Such Department id " + id);
        }
        if(department.getEmployees().isEmpty()) {
            logger.info("No Employees in {}", department.getName());
            throw new NoSuchElementException("No Employees in " + department.getName());
        }
        for (Employee employee : department.getEmployees()) {
            employeesDto.add(employeeMapper.mapEmployeeDto(employee));
        }
        return employeesDto;
    }

}
