package com.i2i.ems.laptop.service;

import java.util.List;

import com.i2i.ems.laptop.dto.LaptopDto;

/**
 * Service interface for Employee entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface LaptopService {

    /**
     * This method is responsible for Save Laptop entity.
     *
     * @param laptopDto - {@link LaptopDto}
     * @return LaptopDto - saved {@link LaptopDto} details.
     */
    LaptopDto saveLaptop(LaptopDto laptopDto);

    /**
     * This method is responsible for retrieve List of Laptops.
     *
     * @return List<LaptopDto> - {@link LaptopDto} details
     */
    List<LaptopDto> retrieveLaptops();

    /**
     * This method is responsible for retrieve Laptop By id.
     *
     * @param id - laptop id.
     * @return - {@link LaptopDto} details.
     */
    LaptopDto retrieveLaptopById(Long id);

    /**
     * This method is responsible for update Employee entity.
     *
     * @param laptopDto - {@link LaptopDto} details.
     * @return - updated {@link LaptopDto} details.
     */
    LaptopDto updateLaptop(LaptopDto laptopDto);

    /**
     * This method is responsible for delete Employee entity.
     *
     * @param id - laptopId.
     * @return true if successfully deleted.
     */
    boolean deleteLaptop(Long id);

}
