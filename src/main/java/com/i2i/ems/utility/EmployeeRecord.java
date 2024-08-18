package com.i2i.ems.utility;

import java.util.ArrayList;
import java.util.List;

import com.i2i.ems.employee.dto.EmployeeDto;
import com.i2i.ems.model.Employee;

/**
 * This class is responsible for get the all employee name
 * in the form List<String>.
 */
public class EmployeeRecord {

    /**
     * This method is responsible for get the all employees name
     * in the form List<String>.
     */
    public static List<String> getListOfEmployees(List<Employee> employees) {
        List<String> string = new ArrayList<>();
        for (Employee employee : employees) {
            string.add(employee.getName());
        }
        return string;
    }

    public static List<String> getListOfEmployeesDto(List<EmployeeDto> employeesDto) {
        List<String> string = new ArrayList<>();
        for (EmployeeDto employeeDto : employeesDto) {
            string.add(employeeDto.getName());
        }
        return string;
    }
}
