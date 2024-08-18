package com.i2i.ems.project.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.mapper.ProjectMapperTest;
import com.i2i.ems.project.service.ProjectService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProjectControllerTest {

    @Mock
    ProjectService projectService;

    @InjectMocks
    ProjectController projectController;

    private ProjectDto projectDto;
    private List<ProjectDto> projectsDto;
    private List<EmployeeDto> employeesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectDto = ProjectMapperTest.getProjectDto();
        projectsDto = ProjectMapperTest.getProjectsDto();
        employeesDto = EmployeeMapperTest.getEmployeesDto();
    }

    @Test
    void testCreateProject() {
        when(projectService.saveProject(projectDto)).thenReturn(projectDto);
        ResponseEntity<ProjectDto> createProject = projectController.createProject(projectDto);
        assertEquals(HttpStatus.CREATED, createProject.getStatusCode());
    }

    @Test
    void testGetProjects() {
        when(projectService.retrieveProjects()).thenReturn(projectsDto);
        ResponseEntity<List<ProjectDto>>  retrieveProjects = projectController.getProjects();
        assertEquals(HttpStatus.OK, retrieveProjects.getStatusCode());
    }

    @Test
    void testGetProjectById() {
        when(projectService.retrieveProjectById(1L)).thenReturn(projectDto);
        ResponseEntity<ProjectDto> retrieveProjectById = projectController.getProjectById(1L);
        assertEquals(HttpStatus.OK, retrieveProjectById.getStatusCode());
    }

    @Test
    void testUpdateProject() {
        when(projectService.updateProject(projectDto)).thenReturn(projectDto);
        ResponseEntity<ProjectDto> updatedProject = projectController.updateProject(projectDto);
        assertEquals(HttpStatus.OK, updatedProject.getStatusCode());
    }

    @Test
    void testDeleteProject() {
        when(projectService.deleteProject(1L)).thenReturn(true);
        ResponseEntity<Boolean> deleteProject = projectController.deleteProject(1L);
        assertEquals(HttpStatus.OK, deleteProject.getStatusCode());
    }

    @Test
    void getEmployeeByProject() {
        when(projectService.retrieveEmployeeByProject(1L)).thenReturn(employeesDto);
        ResponseEntity<List<EmployeeDto>> retrieveEmployeeByProject = projectController.getEmployeeByProject(1L);
        assertEquals(HttpStatus.OK, retrieveEmployeeByProject.getStatusCode());
    }

}