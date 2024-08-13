package com.i2i.ems.project.mapper;

import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;

public class ProjectMapper {
    public static ProjectDto mapProjectDto(Project project) {
        return (ProjectDto) ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .duration(project.getDuration())
                .build();
    }

    public static Project mapProject(ProjectDto projectDto) {
        return Project.builder()
                .name(projectDto.getName())
                .duration(projectDto.getDuration())
                .build();
    }
}
