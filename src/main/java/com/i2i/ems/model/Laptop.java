package com.i2i.ems.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int ram;
    @Column(nullable = false)
    private int rom;
    @Column(name = "is_deleted")
    private boolean isDelete;
    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;
}
