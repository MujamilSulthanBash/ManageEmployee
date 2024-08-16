package com.i2i.ems.utility;

import java.util.ArrayList;
import java.util.List;

import com.i2i.ems.model.Project;

/**
 * This class is responsible for get the all projects name
 * in the form List<String>.
 */
public class ProjectRecord {

    /**
     * This method is responsible for get the all projects name
     * in the form List<String>.
     */
    public static List<String> getListOfProjects(List<Project> projects) {
        List<String> string = new ArrayList<>();
        for (Project project : projects) {
            string.add(project.getName());
        }
        return string;
    }

}
