package com.i2i.ems.laptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ems.model.Laptop;

/**
 * Repository interface for Laptop entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>{

    /**
     * This method is responsible for fetch all Laptop where isDelete is false
     *
     * @return List<Laptop> - {@link Laptop} details
     */
    List<Laptop> findByIsDeleteFalse();

    /**
     * This method is responsible for fetch Laptop By id where isDelete is false
     *
     * @return Laptop - {@link Laptop} details
     */
    Laptop findLaptopByIdAndIsDeleteFalse(Long id);

    /**
     * This method is responsible for check laptop By name where the name is already there or not.
     *
     * @param name - name of the laptop.
     * @return boolean - return true if the name exist or else return false.
     */
    boolean existsByName(String name);

}
