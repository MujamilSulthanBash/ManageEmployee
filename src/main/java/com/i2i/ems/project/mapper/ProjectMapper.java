package com.i2i.ems.project.mapper;

import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;

public class ProjectMapper {
    public static ProjectDto mapProjctDto(Project project) {
        ProjectDto projectDto = ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .duration(project.getDuration())
                .build();
        return projectDto;
    }

    public static Project mapProject(ProjectDto projectDto) {
        Project project =Project.builder()
                .name(projectDto.getName())
                .duration(projectDto.getDuration())
                .build();
        return project;
    }
}
