package com.i2i.ems.laptop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.service.LaptopService;
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

@RestController
@RequestMapping("ems/api/v1/laptops")
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    @PostMapping
    public ResponseEntity<LaptopDto> createEmployee(@RequestBody LaptopDto laptopDto) {
        return new ResponseEntity<>(
                laptopService.saveLaptop(laptopDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<LaptopDto>> getLaptops() {
        List<LaptopDto> laptopsDto = laptopService.retrieveLaptops();
        return new ResponseEntity<>(laptopsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable Long id) {
        return new ResponseEntity<>(
                laptopService.retrieveLaptopById(id),
                HttpStatus.OK
        );
    }

    @PutMapping()
    public ResponseEntity<LaptopDto> updateLaptop(@RequestBody LaptopDto updateLaptopDto) {
        return new ResponseEntity<>(
                laptopService.updateLaptop(updateLaptopDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
