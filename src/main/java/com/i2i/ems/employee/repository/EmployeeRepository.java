package com.i2i.ems.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ems.model.Employee;

/**
 * Repository interface for Employee entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * This method responsible for fetch all Employee where isDelete is false
     *
     * @return List<Employee> - {@link Employee} details
     */
    List<Employee> findByIsDeleteFalse();

    /**
     * This method responsible for fetch Employee By id where isDelete is false
     *
     * @param id - employee id.
     * @return Employee - {@link Employee} details
     */
    Employee findEmployeeByIdAndIsDeleteFalse(Long id);

    /**
     * This method is responsible for check Employee By name where the name is already there or not.
     *
     * @param name - name of the Employee.
     * @return boolean - return true if the name exist or else return false.
     */
    boolean existsByName(String name);

    /**
     * This method is responsible for check Employee By phoneNumber where the phoneNumber is already there or not.
     *
     * @param phoneNumber - phoneNumber of the Employee.
     * @return boolean - return true if the phoneNumber exist or else return false.
     */
    boolean existsByPhoneNumber(String phoneNumber);

}