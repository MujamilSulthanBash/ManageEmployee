package com.i2i.ems.employee.mapper;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.model.Employee;
import com.i2i.ems.utility.YearIntervalCalculator;
import com.i2i.ems.utility.ProjectRecord;
import com.i2i.ems.utility.HideContext;

/**
 * Utility class for mapping between Employee and EmployeeDTO.
 *
 * <p>
 * This class provides static methods for converting between Employee entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class EmployeeMapper {

    /**
     * Converts an {@link Employee} entity to an {@link EmployeeDto}.
     * such as id, name, phoneNumber, email, age, departmentName.
     *
     * @param employee {@link Employee} The Employee entity to be converted.
     * @return {@link EmployeeDto} The corresponding Employee DTO.
     */
    public EmployeeDto mapEmployeeDto(Employee employee)
    {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phoneNumber(HideContext.hideContext(employee.getPhoneNumber()))
                .email(HideContext.hideContext(employee.getEmail()))
                .age(YearIntervalCalculator.calculateAge(employee.getDateOfBirth()))
                .departmentName(employee.getDepartment() == null ?
                        "Not Assigned" : employee.getDepartment().getName())
                .build();
    }

    /**
     * Converts an {@link Employee} entity to an {@link EmployeeDto}.
     * such as id, name, phoneNumber, email, age, departmentName, projectNames.
     *
     * @param employee {@link Employee} The Employee entity to be converted.
     * @return {@link EmployeeDto} The corresponding Employee DTO.
     */
    public EmployeeDto mapEmployeeDtoForProject(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phoneNumber(HideContext.hideContext(employee.getPhoneNumber()))
                .email(HideContext.hideContext(employee.getEmail()))
                .age(YearIntervalCalculator.calculateAge(employee.getDateOfBirth()))
                .departmentName(employee.getDepartment() == null ?
                        "Not Assigned" : employee.getDepartment().getName())
                .projectNames(ProjectRecord.getListOfProjects(employee.getProjects()))
                .build();
    }

    /**
     * Converts an {@link EmployeeDto} to an {@link Employee} entity.
     * such as name, phoneNumber, email, dateOfBirth
     *
     * @param employeeDto {@link EmployeeDto} The Employee DTO to be converted.
     * @return {@link Employee} The corresponding Employee entity.
     */
    public Employee mapEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .build();
    }

}
