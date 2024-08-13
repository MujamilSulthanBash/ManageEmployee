package com.i2i.ems.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Laptop in the organization.
 * This class contains details about the Laptop information,
 * id, name, ram, rom and employee belong to particular Laptop.
 *
 * @author Mujamil
 * @version 0.3
 */
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
