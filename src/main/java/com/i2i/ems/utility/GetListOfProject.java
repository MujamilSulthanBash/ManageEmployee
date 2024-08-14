package com.i2i.ems.utility;

import com.i2i.ems.model.Project;

import java.util.List;

public class GetListOfProject {

    public static String displayProjects(List<Project> projects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Project project : projects) {
            stringBuilder.append(project.getName()).append(", ");
        }
        return stringBuilder.toString();
    }

}
