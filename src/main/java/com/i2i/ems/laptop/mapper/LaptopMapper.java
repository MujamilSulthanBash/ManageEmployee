package com.i2i.ems.laptop.mapper;

import com.i2i.ems.laptop.dto.LaptopDto;
import com.i2i.ems.model.Laptop;

/**
 * Utility class for mapping between Laptop and LaptopDto.
 *
 * <p>
 * This class provides static methods for converting between Laptop entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class LaptopMapper {

    /**
     * Converts an {@link Laptop} entity to an {@link LaptopDto}.
     * such as id, name, ram, rom.
     *
     * @param laptop {@link Laptop} The Employee entity to be converted.
     * @return {@link LaptopDto} The corresponding Employee DTO.
     */
    public static LaptopDto mapLaptopDto(Laptop laptop) {
        return LaptopDto.builder()
                .id(laptop.getId())
                .name(laptop.getName())
                .ram(laptop.getRam())
                .ram(laptop.getRom())
                .build();
    }

    /**
     * Converts an {@link LaptopDto} to an {@link Laptop} entity.
     * such as name, ram, rom.
     *
     * @param laptopDto {@link LaptopDto} The Employee DTO to be converted.
     * @return {@link Laptop} The corresponding Employee entity.
     */
    public static Laptop mapLaptop(LaptopDto laptopDto) {
        return Laptop.builder()
                .name(laptopDto.getName())
                .ram(laptopDto.getRam())
                .rom(laptopDto.getRom())
                .build();
    }

}
