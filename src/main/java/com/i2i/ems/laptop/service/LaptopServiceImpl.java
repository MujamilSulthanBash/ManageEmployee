package com.i2i.ems.laptop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
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

    private static final Logger logger = LogManager.getLogger(LaptopServiceImpl.class);

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public LaptopDto saveLaptop(LaptopDto laptopDto) {
        try {
            Laptop laptop = LaptopMapper.mapLaptop(laptopDto);
            return LaptopMapper.mapLaptopDto(laptopRepository.save(laptop));
        } catch (Exception e) {
            logger.warn("Laptop name {} Already present ", laptopDto.getName());
            throw new DuplicateKeyException("Laptop name " + laptopDto.getName() + " Already present ");
        }
    }

    @Override
    public List<LaptopDto> retrieveLaptops() {
        List<LaptopDto> laptopsDto = new ArrayList<>();
        List<Laptop> laptops = laptopRepository.findByIsDeleteFalse();
        if (laptops.isEmpty()) {
            logger.warn("No Active Laptop in DataBase");
            throw new NoSuchElementException("No Active Laptop in DataBase");
        } else {
            for (Laptop laptop : laptops) {
                laptopsDto.add(LaptopMapper.mapLaptopDto(laptop));
            }
        }
        return laptopsDto;
    }

    @Override
    public LaptopDto retrieveLaptopById(Long id) {
        Laptop laptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(id);
        if (laptop == null) {
            logger.warn("While retrieving laptop there is no Such Laptop id : {}", id);
            throw new NoSuchElementException("No Such Laptop id : " + id);
        } else {
            return LaptopMapper.mapLaptopDto(laptop);
        }
    }

    @Override
    public LaptopDto updateLaptop(LaptopDto laptopDto) {
        Laptop findLaptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(laptopDto.getId());
        if (findLaptop == null) {
            logger.warn("While updating there is no Such Laptop id : {}", laptopDto.getId());
            throw new NoSuchElementException("No Such Laptop id : " + laptopDto.getId());
        } else {
            Laptop laptop = LaptopMapper.mapLaptop(laptopDto);
            logger.info("Employee updated with name {}", laptop.getName());
            return LaptopMapper.mapLaptopDto(laptopRepository.save(laptop));
        }
    }

    @Override
    public void deleteLaptop(Long id) {
        Laptop laptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(id);
        if (laptop == null) {
            logger.warn("While deleting there is no Such Laptop id : {}", id);
            throw new NoSuchElementException("No Such Laptop id : " + id);
        } else {
            laptop.setDelete(true);
            logger.info("Employee deleted with name {}", laptop.getName());
            laptopRepository.save(laptop);
        }
    }

}
