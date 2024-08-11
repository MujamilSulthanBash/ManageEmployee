package com.i2i.ems.laptop.repository;

import com.i2i.ems.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Laptop entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>{
    /**
     * custom Query for fetch all Laptop where isDelete is false
     *
     * @return List<Laptop> - laptop details
     */
    @Query("from Laptop where isDelete = false")
    List<Laptop> findAllLaptop();

    /**
     * custom Query for fetch Laptop By id where isDelete is fasle
     *
     * @return Optional<Laptop> - May or May not return Laptop details
     */
    @Query("from Laptop where id = ?1 and isDelete = false")
    Optional<Laptop> findLaptopById(Long id);
}
