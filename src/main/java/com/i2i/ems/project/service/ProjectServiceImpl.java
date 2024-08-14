package com.i2i.ems.project.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.mapper.ProjectMapper;
import com.i2i.ems.project.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDto saveProject(ProjectDto projectDto) {
        Project project = ProjectMapper.mapProject(projectDto);
        return ProjectMapper.mapProjectDto(projectRepository.save(project));
    }

    @Override
    public List<ProjectDto> retrieveProjects() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> projects = projectRepository.findByIsDeleteFalse();
        for (Project project : projects) {
            projectsDto.add(ProjectMapper.mapProjectDto(project));
        }
        return projectsDto;
    }

    @Override
    public ProjectDto retrieveProjectById(Long id) {
        return ProjectMapper.mapProjectDto(projectRepository.findProjectByIdAndIsDeleteFalse(id));
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = ProjectMapper.mapProject(projectDto);
        return ProjectMapper.mapProjectDto(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findProjectByIdAndIsDeleteFalse(id);
        project.setDelete(true);
        projectRepository.save(project);
    }

    @Override
    public List<EmployeeDto> retrieveEmployeeByProject(Long id) {
        Project project = projectRepository.findProjectByIdAndIsDeleteFalse(id);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for(Employee employee : project.getEmployees()) {
            employeesDto.add(EmployeeMapper.mapEmployeeDtoForProject(employee));
        }
        return employeesDto;
    }

}
