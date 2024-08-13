package com.i2i.ems.department.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ems.department.dto.DepartmentDto;
import com.i2i.ems.department.mapper.DepartmentMapper;
import com.i2i.ems.department.repository.DepartmentRepository;
import com.i2i.ems.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapDepartment(departmentDto);
        return DepartmentMapper.mapDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findByIsDeleteFalse();
        for (Department department : departments) {
            departmentsDto.add(DepartmentMapper.mapDepartmentDto(department));
        }
        return departmentsDto;
    }

    @Override
    public DepartmentDto retrieveDepartmentById(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        return DepartmentMapper.mapDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapDepartment(departmentDto);
        return DepartmentMapper.mapDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findDepartmentByIdAndIsDeleteFalse(id);
        department.setDelete(true);
        departmentRepository.save(department);
    }
}
