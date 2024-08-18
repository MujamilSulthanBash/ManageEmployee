package com.i2i.ems.laptop.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.mapper.LaptopMapperTest;
import com.i2i.ems.laptop.service.LaptopService;

class LaptopControllerTest {

    @Mock
    LaptopService laptopService;

    @InjectMocks
    LaptopController laptopController;

    private LaptopDto laptopDto;
    private List<LaptopDto> laptopsDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        laptopDto = LaptopMapperTest.getLaptopDto();
        laptopsDto = LaptopMapperTest.getLaptopsDto();
    }

    @Test
    void testCreateLaptop() {
        when(laptopService.saveLaptop(laptopDto)).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> createLaptop = laptopController.createLaptop(laptopDto);
        assertEquals(HttpStatus.CREATED, createLaptop.getStatusCode());
    }

    @Test
    void testGetLaptops() {
        when(laptopService.retrieveLaptops()).thenReturn(laptopsDto);
        ResponseEntity<List<LaptopDto>>  retrieveLaptops = laptopController.getLaptops();
        assertEquals(HttpStatus.OK, retrieveLaptops.getStatusCode());
    }

    @Test
    void testGetLaptopById() {
        when(laptopService.retrieveLaptopById(1L)).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> retrieveLaptopById = laptopController.getLaptopById(1L);
        assertEquals(HttpStatus.OK, retrieveLaptopById.getStatusCode());
    }

    @Test
    void testUpdateLaptop() {
        when(laptopService.updateLaptop(laptopDto)).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> updatedLaptop = laptopController.updateLaptop(laptopDto);
        assertEquals(HttpStatus.OK, updatedLaptop.getStatusCode());
    }

    @Test
    void testDeleteLaptop() {
        when(laptopService.deleteLaptop(1L)).thenReturn(true);
        ResponseEntity<Boolean> deleteLaptop = laptopController.deleteLaptop(1L);
        assertEquals(HttpStatus.OK, deleteLaptop.getStatusCode());
    }

}