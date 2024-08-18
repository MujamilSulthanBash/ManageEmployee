package com.i2i.ems.laptop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.mapper.LaptopMapper;
import com.i2i.ems.laptop.repository.LaptopRepository;
import com.i2i.ems.model.Laptop;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private static Logger logger;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private LaptopMapper laptopMapper;

    @Override
    public LaptopDto saveLaptop(LaptopDto laptopDto) {
        if (laptopRepository.existsByName(laptopDto.getName())) {
            logger.warn("Laptop name {} Already present ", laptopDto.getName());
            throw new DuplicateKeyException("Laptop name " + laptopDto.getName() + " Already present ");
        }
        Laptop laptop = laptopMapper.mapLaptop(laptopDto);
        logger.info("Laptop created with name {}", laptop.getName());
        return laptopMapper.mapLaptopDto(laptopRepository.save(laptop));
    }

    @Override
    public List<LaptopDto> retrieveLaptops() {
        List<LaptopDto> laptopsDto = new ArrayList<>();
        List<Laptop> laptops = laptopRepository.findByIsDeleteFalse();
        if (laptops.isEmpty()) {
            logger.warn("No Active Laptop in DataBase");
            throw new NoSuchElementException("No Active Laptop in DataBase");
        }
        for (Laptop laptop : laptops) {
            laptopsDto.add(laptopMapper.mapLaptopDto(laptop));
        }
        return laptopsDto;
    }

    @Override
    public LaptopDto retrieveLaptopById(Long id) {
        Laptop laptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(id);
        if (laptop == null) {
            logger.warn("While retrieving laptop there is no Such Laptop id : {}", id);
            throw new NoSuchElementException("No Such Laptop id : " + id);
        }
        return laptopMapper.mapLaptopDto(laptop);
    }

    @Override
    public LaptopDto updateLaptop(LaptopDto laptopDto) {
        Laptop findLaptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(laptopDto.getId());
        if (findLaptop == null) {
            logger.warn("While updating there is no Such Laptop id : {}", laptopDto.getId());
            throw new NoSuchElementException("No Such Laptop id : " + laptopDto.getId());
        }
        Laptop laptop = laptopMapper.mapLaptop(laptopDto);
        logger.info("Laptop updated with name {}", laptop.getName());
        return laptopMapper.mapLaptopDto(laptopRepository.save(laptop));
    }

    @Override
    public boolean deleteLaptop(Long id) {
        Laptop laptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(id);
        if (laptop == null) {
            logger.warn("While deleting there is no Such Laptop id : {}", id);
            throw new NoSuchElementException("No Such Laptop id : " + id);
        }
        laptop.setDelete(true);
        logger.info("Laptop deleted with name {}", laptop.getName());
        laptopRepository.save(laptop);
        return true;
    }

}
