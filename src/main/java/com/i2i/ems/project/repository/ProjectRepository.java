package com.i2i.ems.project.repository;

import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Project entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    /**
     * custom Query for fetch all Project where isDelete is false
     *
     * @return List<Project> - Project details
     */
    @Query("from Project where isDelete = false")
    List<Project> findAllProject();

    /**
     * custom Query for fetch Project By id where isDelete is false
     *
     * @return Optional<Project> - May or May not return Project details
     */
    @Query("from Project where id = ?1 and isDelete = false")
    Optional<Project> findProjectById(Long id);
}
