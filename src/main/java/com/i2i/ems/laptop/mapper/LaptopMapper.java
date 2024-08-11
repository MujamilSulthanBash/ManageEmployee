package com.i2i.ems.laptop.mapper;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.model.Laptop;

public class LaptopMapper {

    public static LaptopDto mapLaptopDto(Laptop laptop) {
        LaptopDto laptopDto = LaptopDto.builder()
                .id(laptop.getId())
                .name(laptop.getName())
                .ram(laptop.getRam())
                .ram(laptop.getRom())
                .build();
        return laptopDto;
    }

    public static Laptop mapLaptop(LaptopDto laptopDto) {
        Laptop laptop = Laptop.builder()
                .name(laptopDto.getName())
                .ram(laptopDto.getRam())
                .rom(laptopDto.getRom())
                .build();
        return laptop;
    }
}
