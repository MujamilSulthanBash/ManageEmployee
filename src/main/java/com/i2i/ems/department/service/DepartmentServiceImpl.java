package com.i2i.ems.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
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

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        try {
            Department department = DepartmentMapper.mapDepartment(departmentDto);
            logger.info("Department created with name {}", department.getName());
            return DepartmentMapper.mapDepartmentDto(departmentRepository.save(department));
        } catch (Exception e) {
            logger.warn("Department name {} Already present ", departmentDto.getName());
            throw new DuplicateKeyException("Department name " +departmentDto.getName() + " Already present ");
        }
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findByIsDeleteFalse();
        if (departments.isEmpty()) {
            logger.warn("No Active Department in DataBase");
            throw new NoSuchElementException("No Active Department in DataBase");
        } else {
            for (Department department : departments) {
                departmentsDto.add(DepartmentMapper.mapDepartmentDto(department));
            }
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
        return DepartmentMapper.mapDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department departmentCheck = departmentRepository.findDepartmentByIdAndIsDeleteFalse(departmentDto.getId());
        if (departmentCheck == null) {
            logger.info("No Such Department id : {}", departmentDto.getId());
            throw new NoSuchElementException("No Such Department id : " + departmentDto.getId());
        } else {
            Department department = DepartmentMapper.mapDepartment(departmentDto);
            logger.info("Department updated with name {}", department.getName());
            return DepartmentMapper.mapDepartmentDto(departmentRepository.save(department));
        }
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        if (department == null) {
            logger.info("While deleting there is no Such Department id : {}", id);
            throw new NoSuchElementException("No Such Department id : " + id);
        } else {
            department.setDelete(true);
            logger.info("Department deleted with name {}", department.getName());
            departmentRepository.save(department);
        }
    }

    @Override
    public List<EmployeeDto> retrieveEmployeeByDepartment(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        if (department == null) {
            logger.info("No such id {}", id);
            throw new NoSuchElementException("No Such Department id " + id);
        } else {
            if(department.getEmployees().isEmpty()) {
                logger.info("No Employee in {}", department.getName());
                throw new NoSuchElementException("No Employee in " + department.getName());
            } else {
                for (Employee employee : department.getEmployees()) {
                    employeesDto.add(EmployeeMapper.mapEmployeeDto(employee));
                }
            }
        }
        return employeesDto;
    }

}
