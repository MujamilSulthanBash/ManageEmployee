package com.i2i.ems.project.controller;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.mapper.ProjectMapper;
import com.i2i.ems.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ems/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity(
                ProjectMapper.mapProjctDto(
                        projectService.saveProject(
                                ProjectMapper.mapProject(
                                        projectDto
                                )
                        )
                ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        List<Project> projects = projectService.retrieveProjects();
        for (Project project : projects) {
            projectDtos.add(ProjectMapper.mapProjctDto(project));
        }
        return new ResponseEntity(projectDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return new ResponseEntity(
                ProjectMapper.mapProjctDto(
                        projectService.retrieveProjectById(id)
                ), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto updateProjectDto) {
        Project project = projectService.retrieveProjectById(id);
        Project updatedProject = ProjectMapper.mapProject(updateProjectDto);
        updatedProject.setId(project.getId());
        return new ResponseEntity(
                ProjectMapper.mapProjctDto(
                        projectService.updateProject(
                                updatedProject)
                ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Project project = projectService.retrieveProjectById(id);
        projectService.deleteProject(project);
        return new ResponseEntity(HttpStatus.OK);
    }
}
