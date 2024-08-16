package com.i2i.ems.laptop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.service.LaptopService;

/**
 * REST controller for managing Laptop-related operations.
 *
 * <p>
 * This controller handles HTTP requests and provides endpoints for
 * creating, retrieving, updating, and deleting Laptop entities. The
 * controller maps client requests to the appropriate service methods
 * and returns responses in the form of JSON or other supported media types.
 * It is annotated with Spring MVC annotations to define the URL mappings
 * and request handling logic.
 * All responses are returned in a standardized format to ensure consistency across
 * the API.
 * </p>
 */
@RestController
@RequestMapping("ems/api/v1/laptops")
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    /**
     * Creates a new laptop.
     *
     * @param laptopDto {@link LaptopDto} The DTO containing laptop data.
     * @return The created {@link LaptopDto} with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<LaptopDto> createLaptop(@RequestBody LaptopDto laptopDto) {
        return new ResponseEntity<>(laptopService.saveLaptop(laptopDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all laptops.
     *
     * @return the list of all laptops as {@link LaptopDto} with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<LaptopDto>> getLaptops() {
        List<LaptopDto> laptopsDto = laptopService.retrieveLaptops();
        return new ResponseEntity<>(laptopsDto, HttpStatus.OK);
    }

    /**
     * Retrieves laptop by their unique laptop ID.
     *
     * @param id the unique laptop ID
     * @return the {@link LaptopDto} if found with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable Long id) {
        return new ResponseEntity<>(laptopService.retrieveLaptopById(id), HttpStatus.OK);
    }

    /**
     * Updates an existing laptop's details.
     *
     * @param updateLaptopDto - {@link LaptopDto} details.
     * @return the updated {@link LaptopDto} with HTTP status 200 OK.
     */
    @PutMapping()
    public ResponseEntity<LaptopDto> updateLaptop(@RequestBody LaptopDto updateLaptopDto) {
        return new ResponseEntity<>(laptopService.updateLaptop(updateLaptopDto), HttpStatus.OK);
    }

    /**
     * Deletes an laptop by their unique laptop ID.
     *
     * @param id the unique laptop ID
     * @return HTTP status 200 OK.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
