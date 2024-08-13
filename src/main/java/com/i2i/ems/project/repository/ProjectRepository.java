package com.i2i.ems.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ems.model.Project;

/**
 * Repository interface for Project entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * This method is responsible for fetch all Project where isDelete is false
     *
     * @return List<Project> - {@link Project} details
     */
    List<Project> findByIsDeleteFalse();

    /**
     * This method is responsible for fetch Project By id where isDelete is false
     *
     * @return Project - {@link Project} details
     */
    Project findProjectByIdAndIsDeleteFalse(Long id);

}
