package com.i2i.ems.project.service;

import com.i2i.ems.model.Project;
import com.i2i.ems.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> retrieveProjects() {
        return projectRepository.findAllProject();
    }

    @Override
    public Project retrieveProjectById(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.save(project);
    }
}
