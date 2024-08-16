package com.i2i.ems.project.mapper;

import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;

/**
 * Utility class for mapping between Project and ProjectDto.
 *
 * <p>
 * This class provides static methods for converting between Project entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class ProjectMapper {

    /**
     * Converts an {@link Project} entity to an {@link ProjectDto}.
     * such as id, name, duration.
     *
     * @param project {@link Project} The Employee entity to be converted.
     * @return {@link ProjectDto} The corresponding Employee DTO.
     */
    public static ProjectDto mapProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .duration(project.getDuration())
                .build();
    }

    /**
     * Converts an {@link ProjectDto} to an {@link Project} entity.
     * such as name, duration.
     *
     * @param projectDto {@link ProjectDto} The Employee DTO to be converted.
     * @return {@link Project} The corresponding Employee entity.
     */
    public static Project mapProject(ProjectDto projectDto) {
        return Project.builder()
                .name(projectDto.getName())
                .duration(projectDto.getDuration())
                .build();
    }

}
