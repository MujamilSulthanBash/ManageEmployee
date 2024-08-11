package com.i2i.ems.laptop.controller;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.mapper.LaptopMapper;
import com.i2i.ems.laptop.service.LaptopService;
import com.i2i.ems.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ems/api/v1/laptops")
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    @PostMapping
    public ResponseEntity<LaptopDto> createEmployee(@RequestBody LaptopDto laptopDto) {
        return new ResponseEntity(
                LaptopMapper.mapLaptopDto(
                        laptopService.saveLaptop(
                                LaptopMapper.mapLaptop(
                                        laptopDto
                                )
                        )
                ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LaptopDto>> getLaptops() {
        List<LaptopDto> laptopDtos = new ArrayList<>();
        List<Laptop> laptops = laptopService.retrieveLaptops();
        for (Laptop laptop : laptops) {
            laptopDtos.add(LaptopMapper.mapLaptopDto(laptop));
        }
        return new ResponseEntity(laptopDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable Long id) {
        return new ResponseEntity(
                LaptopMapper.mapLaptopDto(
                        laptopService.retrieveLaptopById(id)
                ), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaptopDto> updateLaptop(@PathVariable Long id, @RequestBody LaptopDto updateLaptopDto) {
        Laptop laptop = laptopService.retrieveLaptopById(id);
        Laptop updatedLaptop = LaptopMapper.mapLaptop(updateLaptopDto);
        updatedLaptop.setId(laptop.getId());
        return new ResponseEntity(
                LaptopMapper.mapLaptopDto(
                        laptopService.updateLaptop(
                                updatedLaptop)
                ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable Long id) {
        Laptop laptop = laptopService.retrieveLaptopById(id);
        laptopService.deleteLaptop(laptop);
        return new ResponseEntity(HttpStatus.OK);
    }
}
