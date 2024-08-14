package com.i2i.ems.utility;

import com.i2i.ems.model.Employee;

import java.util.List;

public class GetListOfEmployee {

    public static String displayEmployees(List<Employee> employees) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Employee employee : employees) {
            stringBuilder.append(employee.getName()).append(", ");
        }
        return stringBuilder.toString();
    }

}
