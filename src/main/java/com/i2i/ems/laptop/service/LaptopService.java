package com.i2i.ems.laptop.service;

import com.i2i.ems.model.Laptop;

import java.util.List;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface LaptopService {
    /**
     * Save Laptop entity.
     *
     * @param laptop - laptop details.
     * @return the saved laptop.
     */
    Laptop saveLaptop(Laptop laptop);

    /**
     * retrieve set of Laptops.
     *
     * @return list of laptops.
     */
    List<Laptop> retrieveLaptops();

    /**
     * retrieve laptop By id.
     *
     * @param id - laptop id.
     * @return the saved laptop.
     */
    Laptop retrieveLaptopById(Long id);

    /**
     * update Laptop entity.
     *
     * @param laptop - laptop details.
     * @return the updated Laptop.
     */
    Laptop updateLaptop(Laptop laptop);

    /**
     * delete Laptop entity.
     *
     * @param laptop - laptop details.
     */
    void deleteLaptop(Laptop laptop);
}
