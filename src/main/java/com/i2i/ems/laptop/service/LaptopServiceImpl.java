package com.i2i.ems.laptop.service;

import com.i2i.ems.laptop.repository.LaptopRepository;
import com.i2i.ems.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public List<Laptop> retrieveLaptops() {
        return laptopRepository.findAllLaptop();
    }

    @Override
    public Laptop retrieveLaptopById(Long id) {
        Optional<Laptop> laptop = laptopRepository.findLaptopById(id);
        return laptop.orElse(null);
    }

    @Override
    public Laptop updateLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public void deleteLaptop(Laptop laptop) {
        laptop.setDelete(true);
        laptopRepository.save(laptop);
    }
}
