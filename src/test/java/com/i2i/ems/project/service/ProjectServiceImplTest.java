package com.i2i.ems.project.service;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.employee.mapper.EmployeeMapper;
import com.i2i.ems.employee.mapper.EmployeeMapperTest;
import com.i2i.ems.model.Employee;
import com.i2i.ems.model.Project;
import com.i2i.ems.project.dto.ProjectDto;
import com.i2i.ems.project.mapper.ProjectMapper;
import com.i2i.ems.project.mapper.ProjectMapperTest;
import com.i2i.ems.project.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    ProjectServiceImpl projectService;

    private Project project;
    private ProjectDto projectDto;
    private List<Project> projects;
    private Employee employee;
    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = ProjectMapperTest.getProject();
        projectDto = ProjectMapperTest.getProjectDto();
        projects = ProjectMapperTest.getProjects();
        employee = EmployeeMapperTest.getEmployee();
        employeeDto =EmployeeMapperTest.getEmployeeDto();
        employeesDto = EmployeeMapperTest.getEmployeesDto();
    }

    @Test
    void testSaveProjectSuccess() {
        when(projectRepository.existsByName(projectDto.getName())).thenReturn(false);
        when(projectMapper.mapProject(projectDto)).thenReturn(project);
        when(projectMapper.mapProjectDto(project)).thenReturn(projectDto);
        when(projectRepository.save(project)).thenReturn(project);
        ProjectDto savedProjectDto = projectService.saveProject(projectDto);
        assertEquals("front end", savedProjectDto.getName());
    }

    @Test
    void testSaveProjectFailure() {
        when(projectRepository.existsByName(projectDto.getName())).thenReturn(true);
        DuplicateKeyException duplicateKeyException = assertThrows(DuplicateKeyException.class, () -> {
            projectService.saveProject(projectDto); });
        assertEquals("Project name front end Already present ", duplicateKeyException.getMessage());
    }

    @Test
    void testRetrieveProjectsSuccess() {
        when(projectRepository.findByIsDeleteFalse()).thenReturn(projects);
        when(projectMapper.mapProject(projectDto)).thenReturn(project);
        List<ProjectDto> result = projectService.retrieveProjects();
        assertEquals(1, result.size());
    }

    @Test
    void testRetrieveProjectsFailure() {
        when(projectRepository.findByIsDeleteFalse()).thenReturn(new ArrayList<>());
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            projectService.retrieveProjects(); });
        assertEquals("No Active Project in DataBase", noSuchElementException.getMessage());
    }

    @Test
    void testRetrieveProjectByIdSuccess() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(project);
        when(projectMapper.mapProjectDto(project)).thenReturn(projectDto);
        ProjectDto result = projectService.retrieveProjectById(1L);
        assertEquals("front end", result.getName());
    }

    @Test
    void testRetrieveProjectByIdFailure() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            projectService.retrieveProjectById(1L); });
        assertEquals("No Such Project id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testReadProjectByIdSuccess() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(project);
        Project result = projectService.readProjectById(1L);
        assertEquals("front end", result.getName());
    }

    @Test
    void testReadProjectByIdFailure() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            projectService.readProjectById(1L); });
        assertEquals("No Such Project id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testUpdateProjectSuccess() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(project);
        when(projectMapper.mapProject(projectDto)).thenReturn(project);
        when(projectMapper.mapProjectDto(project)).thenReturn(projectDto);
        when(projectRepository.save(project)).thenReturn(project);
        ProjectDto updateProject = projectService.updateProject(projectDto);
        assertEquals("front end", updateProject.getName());
    }

    @Test
    void testUpdateProjectFailure() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            projectService.updateProject(projectDto); });
        assertEquals("No Such Project id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testDeleteProjectSuccess() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        Project checkProject = projectRepository.save(project);
        assertEquals("front end", checkProject.getName());
    }

    @Test
    void testDeleteProjectFailure() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            projectService.deleteProject(1L); });
        assertEquals("No Such Project id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testRetrieveEmployeeByProjectSuccess() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(project);
        when(employeeMapper.mapEmployeeDto(employee)).thenReturn(employeeDto);
        List<EmployeeDto> result = projectService.retrieveEmployeeByProject(1L);
        assertEquals(employeesDto.size(), result.size());
    }

    @Test
    public void testRetrieveEmployeeByProjectFailure() {
        when(projectRepository.findProjectByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            projectService.retrieveProjectById(1L);
        });
        assertEquals("No Such Project id : 1", noSuchElementException.getMessage());
    }
}