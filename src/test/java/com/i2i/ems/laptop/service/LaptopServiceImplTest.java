package com.i2i.ems.laptop.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.mapper.LaptopMapper;
import com.i2i.ems.laptop.mapper.LaptopMapperTest;
import com.i2i.ems.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.i2i.ems.laptop.repository.LaptopRepository;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class LaptopServiceImplTest {

    @Mock
    LaptopRepository laptopRepository;

    @Mock
    LaptopMapper laptopMapper;

    @InjectMocks
    LaptopServiceImpl laptopService;

    private LaptopDto laptopDto;
    private Laptop laptop;
    private List<Laptop> laptops;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        laptopDto = LaptopMapperTest.getLaptopDto();
        laptop = LaptopMapperTest.getLaptop();
        laptops = LaptopMapperTest.getLaptops();
    }

    @Test
    void testSaveLaptopSuccess() {
        when(laptopRepository.existsByName(laptopDto.getName())).thenReturn(false);
        when(laptopMapper.mapLaptop(laptopDto)).thenReturn(laptop);
        when(laptopMapper.mapLaptopDto(laptop)).thenReturn(laptopDto);
        when(laptopRepository.save(laptop)).thenReturn(laptop);
        LaptopDto savedLaptopDto = laptopService.saveLaptop(laptopDto);
        assertEquals("apple", savedLaptopDto.getName());
    }

    @Test
    void testSaveLaptopFailure() {
        when(laptopRepository.existsByName(laptopDto.getName())).thenReturn(true);
        DuplicateKeyException duplicateKeyException = assertThrows(DuplicateKeyException.class, () -> {
            laptopService.saveLaptop(laptopDto); });
        assertEquals("Laptop name apple Already present ", duplicateKeyException.getMessage());
    }

    @Test
    void testRetrieveLaptopsSuccess() {
        when(laptopRepository.findByIsDeleteFalse()).thenReturn(laptops);
        when(laptopMapper.mapLaptop(laptopDto)).thenReturn(laptop);
        List<LaptopDto> result = laptopService.retrieveLaptops();
        assertEquals(1, result.size());
    }

    @Test
    void testRetrieveLaptopsFailure() {
        when(laptopRepository.findByIsDeleteFalse()).thenReturn(new ArrayList<>());
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            laptopService.retrieveLaptops(); });
        assertEquals("No Active Laptop in DataBase", noSuchElementException.getMessage());
    }

    @Test
    void testRetrieveLaptopByIdSuccess() {
        when(laptopRepository.findLaptopByIdAndIsDeleteFalse(1L)).thenReturn(laptop);
        when(laptopMapper.mapLaptopDto(laptop)).thenReturn(laptopDto);
        LaptopDto result = laptopService.retrieveLaptopById(1L);
        assertEquals("apple", result.getName());
    }

    @Test
    void testRetrieveLaptopByIdFailure() {
        when(laptopRepository.findLaptopByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            laptopService.retrieveLaptopById(1L); });
        assertEquals("No Such Laptop id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testUpdateLaptopSuccess() {
        when(laptopRepository.findLaptopByIdAndIsDeleteFalse(1L)).thenReturn(laptop);
        when(laptopMapper.mapLaptop(laptopDto)).thenReturn(laptop);
        when(laptopMapper.mapLaptopDto(laptop)).thenReturn(laptopDto);
        when(laptopRepository.save(laptop)).thenReturn(laptop);
        LaptopDto updateLaptop = laptopService.updateLaptop(laptopDto);
        assertEquals("apple", updateLaptop.getName());
    }

    @Test
    void testUpdateLaptopFailure() {
        when(laptopRepository.findLaptopByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            laptopService.updateLaptop(laptopDto); });
        assertEquals("No Such Laptop id : 1", noSuchElementException.getMessage());
    }

    @Test
    void testDeleteLaptopSuccess() {
        when(laptopRepository.findLaptopByIdAndIsDeleteFalse(1L)).thenReturn(laptop);
        when(laptopRepository.save(laptop)).thenReturn(laptop);
        Laptop checkLaptop = laptopRepository.save(laptop);
        assertEquals("apple", checkLaptop.getName());
    }

    @Test
    void testDeleteLaptopFailure() {
        when(laptopRepository.findLaptopByIdAndIsDeleteFalse(1L)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            laptopService.deleteLaptop(1L); });
        assertEquals("No Such Laptop id : 1", noSuchElementException.getMessage());
    }
}