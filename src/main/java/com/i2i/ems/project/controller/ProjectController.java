package com.i2i.ems.project.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.service.ProjectService;

/**
 * REST controller for managing Project-related operations.
 *
 * <p>
 * This controller handles HTTP requests and provides endpoints for
 * creating, retrieving, updating, and deleting Project entities. The
 * controller maps client requests to the appropriate service methods
 * and returns responses in the form of JSON or other supported media types.
 * It is annotated with Spring MVC annotations to define the URL mappings
 * and request handling logic.
 * All responses are returned in a standardized format to ensure consistency across
 * the API.
 * </p>
 */
@RestController
@RequestMapping("ems/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Creates a new project.
     *
     * @param projectDto {@link ProjectDto} The DTO containing project data.
     * @return The created {@link ProjectDto} with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.saveProject(projectDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all projects.
     *
     * @return the list of all projects as {@link ProjectDto} with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<ProjectDto> projectsDto = projectService.retrieveProjects();
        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }

    /**
     * Retrieves an project by their unique project ID.
     *
     * @param id the unique project ID
     * @return the {@link ProjectDto} if found with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.retrieveProjectById(id), HttpStatus.OK);
    }

    /**
     * Updates an existing project's details.
     *
     * @param updateProjectDto - {@link ProjectDto} details.
     * @return the updated {@link ProjectDto} with HTTP status 200 OK.
     */
    @PutMapping()
    public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto updateProjectDto) {
        return new ResponseEntity<>(projectService.updateProject(updateProjectDto), HttpStatus.OK);
    }

    /**
     * Deletes an project by their unique project ID.
     *
     * @param id the unique project ID
     * @return HTTP status 200 OK.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves a list of all employees based on project id.
     *
     * @return the list of all Employee as {@link EmployeeDto} with HTTP status 200 OK.
     */
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByProject(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.retrieveEmployeeByProject(id), HttpStatus.OK);
    }

}
