package com.i2i.ems.project.mapper;

import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapperTest {

    public static ProjectDto getProjectDto() {
        return ProjectDto.builder()
                .id(1L)
                .name("front end")
                .duration(4)
                .build();
    }

    public static Project getProject() {
        return Project.builder()
                .name("front end")
                .duration(4)
                .employees(EmployeeMapperTest.getEmployees())
                .build();
    }

    public static List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(getProject());
        return projects;
    }

    public static List<ProjectDto> getProjectsDto() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        projectsDto.add(getProjectDto());
        return projectsDto;
    }

}
