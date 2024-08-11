package com.i2i.ems.laptop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
    private Long id;
    private String name;
    private int ram;
    private int rom;
}
