package com.i2i.ems.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private String name;
    private int duration;
    @Column(name = "is_delete")
    private boolean isDelete;
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;
}
