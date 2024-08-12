package com.i2i.ems.department.service;

import com.i2i.ems.department.repository.DepartmentRepository;
import com.i2i.ems.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> retrieveDepartments() {
        return departmentRepository.findAllDepartment();
    }

    @Override
    public Department retrieveDepartmentById(Long id) {
        return departmentRepository.findDepartmentById(id);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentRepository.save(department);
    }
}
