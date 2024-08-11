package com.i2i.ems.department.repository;

import com.i2i.ems.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Department entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * custom Query for fetch all Department where isDelete is true
     *
     * @return List<Department> - Department details
     */
    @Query("from Department where isDelete = false")
    List<Department> findAllDepartment();

    /**
     * custom Query for fetch Department By id where isDelete is true
     *
     * @return Optional<Department> - May or May not return Department details
     */
    @Query("from Department where id = ?1 and isDelete = false")
    Optional<Department> findDepartmentById(Long id);
}
