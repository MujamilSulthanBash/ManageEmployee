package com.i2i.ems.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ems.model.Department;

/**
 * Repository interface for Department entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * This method is responsible for fetch all Department where isDelete is true
     *
     * @return List<Department> - {@link Department} details
     */
    List<Department> findByIsDeleteFalse();

    /**
     * This method is responsible for fetch Department By id where isDelete is true
     *
     * @return Department - {@link Department} details
     */
    Department findDepartmentByIdAndIsDeleteFalse(Long id);

    /**
     * This method is responsible for check Department By name where the name is already there or not.
     *
     * @param name - name of the department.
     * @return boolean - return true if the name exist or else return false.
     */
    boolean existsByName(String name);

}
