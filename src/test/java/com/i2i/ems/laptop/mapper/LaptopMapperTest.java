package com.i2i.ems.laptop.mapper;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.model.Laptop;

import java.util.ArrayList;
import java.util.List;

public class LaptopMapperTest {
    public static LaptopDto getLaptopDto() {
        return LaptopDto.builder()
                .id(1L)
                .name("apple")
                .ram(16)
                .ram(500)
                .build();
    }

    public static Laptop getLaptop() {
        return Laptop.builder()
                .id(1L)
                .name("apple")
                .ram(16)
                .rom(500)
                .build();
    }

    public static List<Laptop> getLaptops() {
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(getLaptop());
        return laptops;
    }

    public static List<LaptopDto> getLaptopsDto() {
        List<LaptopDto> laptopsDto = new ArrayList<>();
        laptopsDto.add(getLaptopDto());
        return laptopsDto;
    }
}
