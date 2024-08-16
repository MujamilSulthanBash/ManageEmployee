package com.i2i.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a Project in the organization.
 * This class contains details about the Project information,
 * id, name, duration of the project and employees belong to the particular project.
 *
 * @author Mujamil
 * @version 0.3
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int duration;
    @Column(name = "is_delete")
    private boolean isDelete;
    @ManyToMany(mappedBy = "projects")
    @Column(unique = true)
    private List<Employee> employees;
}
