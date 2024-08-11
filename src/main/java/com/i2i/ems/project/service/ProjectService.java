package com.i2i.ems.project.service;

import com.i2i.ems.model.Project;

import java.util.List;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface ProjectService {
    /**
     * Save Project entity.
     *
     * @param project - project details.
     * @return the saved project.
     */
    Project saveProject(Project project);

    /**
     * retrieve set of Projects.
     *
     * @return list of projects.
     */
    List<Project> retrieveProjects();

    /**
     * retrieve Project By id.
     *
     * @param id - project id.
     * @return the saved project.
     */
    Project retrieveProjectById(Long id);

    /**
     * update Project entity.
     *
     * @param project - project details.
     * @return the updated project.
     */
    Project updateProject(Project project);

    /**
     * delete Project entity.
     *
     * @param project - project details.
     */
    void deleteProject(Project project);

}
