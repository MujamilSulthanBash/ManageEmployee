package com.i2i.ems.employee.repository;

import com.i2i.ems.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    @Query("from Employee where isDelete = false")
    List<Employee> findAllEmployee();

    /**
     * custom Query for fetch Employee By id where isDelete is false
     *
     * @return Optional<Employee> - May or May not return Employee details
     */
    @Query("from Employee where id = ?1 and isDelete = false")
    Optional<Employee> findEmployeeById(Long id);
}

