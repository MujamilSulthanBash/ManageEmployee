package com.i2i.ems.employee.repository;

import java.util.List;

import com.i2i.ems.model.Department;
import com.i2i.ems.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i2i.ems.model.Employee;

/**
 * Repository interface for Employee entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * custom Query for fetch all Employee where isDelete is false
     *
     * @return List<Employee> - Department details
     */
    List<Employee> findByIsDeleteFalse();

    /**
     * custom Query for fetch Employee By id where isDelete is false
     *
     * @param id - Employee id.
     * @return Optional<Employee> - May or May not return Employee details
     */
    Employee findEmployeeByIdAndIsDeleteFalse(Long id);

}