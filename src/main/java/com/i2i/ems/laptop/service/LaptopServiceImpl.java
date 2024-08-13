package com.i2i.ems.laptop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.laptop.mapper.LaptopMapper;
import com.i2i.ems.laptop.repository.LaptopRepository;
import com.i2i.ems.model.Laptop;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public LaptopDto saveLaptop(LaptopDto laptopDto) {
        Laptop laptop = LaptopMapper.mapLaptop(laptopDto);
        return LaptopMapper.mapLaptopDto(laptopRepository.save(laptop));
    }

    @Override
    public List<LaptopDto> retrieveLaptops() {
        List<LaptopDto> laptopsDto = new ArrayList<>();
        List<Laptop> laptops = laptopRepository.findByIsDeleteFalse();
        for (Laptop laptop : laptops) {
            laptopsDto.add(LaptopMapper.mapLaptopDto(laptop));
        }
        return laptopsDto;
    }

    @Override
    public LaptopDto retrieveLaptopById(Long id) {
        Laptop laptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(id);
        return LaptopMapper.mapLaptopDto(laptop);
    }

    @Override
    public LaptopDto updateLaptop(LaptopDto laptopDto) {
        Laptop laptop = LaptopMapper.mapLaptop(laptopDto);
        return LaptopMapper.mapLaptopDto(laptopRepository.save(laptop));
    }

    @Override
    public void deleteLaptop(Long id) {
        Laptop laptop = laptopRepository.findLaptopByIdAndIsDeleteFalse(id);
        laptop.setDelete(true);
        laptopRepository.save(laptop);
    }

}
