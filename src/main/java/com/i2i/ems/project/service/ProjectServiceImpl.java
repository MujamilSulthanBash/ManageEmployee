package com.i2i.ems.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.mapper.ProjectMapper;
import com.i2i.ems.project.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LogManager.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ProjectDto saveProject(ProjectDto projectDto) {
        if (projectRepository.existsByName(projectDto.getName())) {
            logger.warn("Project name {} Already present ", projectDto.getName());
            throw new DuplicateKeyException("Project name " + projectDto.getName() + " Already present ");
        }
        Project project = projectMapper.mapProject(projectDto);
        logger.info("Project created with name {}", project.getName());
        return projectMapper.mapProjectDto(projectRepository.save(project));
    }

    @Override
    public List<ProjectDto> retrieveProjects() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> projects = projectRepository.findByIsDeleteFalse();
        if (projects.isEmpty()) {
            logger.info("No Active Project in DataBase");
            throw new NoSuchElementException("No Active Project in DataBase");
        }
        for (Project project : projects) {
            projectsDto.add(projectMapper.mapProjectDto(project));
        }
        return projectsDto;
    }

    @Override
    public ProjectDto retrieveProjectById(Long id) {
        Project project = projectRepository.findProjectByIdAndIsDeleteFalse(id);
        if (project == null) {
            logger.info("While retrieving No Such Project id : {}", id);
            throw new NoSuchElementException("No Such Project id : " + id);
        }
        return projectMapper.mapProjectDto(project);
    }

    @Override
    public Project readProjectById(Long id) {
        Project project = projectRepository.findProjectByIdAndIsDeleteFalse(id);
        if (project == null) {
            logger.info("No Such Project id : {}", id);
            throw new NoSuchElementException("No Such Project id : " + id);
        }
        return project;
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project projectCheck = projectRepository.findProjectByIdAndIsDeleteFalse(projectDto.getId());
        if (projectCheck == null) {
            logger.info("While updating there is No Such Project id : {}", projectDto.getId());
            throw new NoSuchElementException("No Such Project id : " + projectDto.getId());
        }
        Project project = projectMapper.mapProject(projectDto);
        logger.info("Project updated with id {}", project.getName());
        return projectMapper.mapProjectDto(projectRepository.save(project));
    }

    @Override
    public boolean deleteProject(Long id) {
        Project project = projectRepository.findProjectByIdAndIsDeleteFalse(id);
        if (project == null) {
            logger.info("While deleting there is No Such Project id : {}", id);
            throw new NoSuchElementException("No Such Project id : " + id);
        }
        project.setDelete(true);
        logger.info("Project deleted with id {}", project.getName());
        projectRepository.save(project);
        return true;
    }

    @Override
    public List<EmployeeDto> retrieveEmployeeByProject(Long id) {
        Project project = projectRepository.findProjectByIdAndIsDeleteFalse(id);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        if(project == null) {
            logger.info("No such project id {}", id);
            throw new NoSuchElementException("No such project id " + id);
        }
        if (project.getEmployees().isEmpty()) {
            logger.info("No Employees in project {}", project.getName());
            throw new NoSuchElementException("No Employees in project" + project.getName());
        }
        for (Employee employee : project.getEmployees()) {
            employeesDto.add(employeeMapper.mapEmployeeDto(employee));
        }
        return employeesDto;
    }

}
