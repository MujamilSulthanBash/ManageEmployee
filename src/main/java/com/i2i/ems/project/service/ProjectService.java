package com.i2i.ems.project.service;

import java.util.List;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.project.dto.ProjectDto;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface ProjectService {

    /**
     * This method is responsible for Save Project entity.
     *
     * @param projectDto - {@link ProjectDto}
     * @return ProjectDto - saved {@link ProjectDto} details.
     */
    ProjectDto saveProject(ProjectDto projectDto);

    /**
     * This method is responsible for retrieve List of Projects.
     *
     * @return List<ProjectDto> - {@link ProjectDto} details
     */
    List<ProjectDto> retrieveProjects();

    /**
     * This method is responsible for retrieve project By id.
     *
     * @param id - project id.
     * @return - {@link ProjectDto} details.
     */
    ProjectDto retrieveProjectById(Long id);

    /**
     * This method is responsible for update project entity.
     *
     * @param projectDto - {@link ProjectDto} details.
     * @return - updated {@link ProjectDto} details.
     */
    ProjectDto updateProject(ProjectDto projectDto);

    /**
     * This method is responsible for delete project entity.
     *
     * @param id - employee id.
     */
    void deleteProject(Long id);

    List<EmployeeDto> retrieveEmployeeByProject(Long id);
}
