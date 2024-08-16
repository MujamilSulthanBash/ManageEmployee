package com.i2i.ems.laptop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 *     Defines blueprint for Laptop entity for creation operation,
 *     input should be given in this defined format.
 *     such as id, name, ram, rom.
 * </p>
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
    private Long id;
    @NotBlank(message = "should contain alphabets")
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Name should be alphabets" )
    private String name;
    @NotBlank(message = "should contain alphabets")
    private int ram;
    @NotBlank(message = "should contain alphabets")
    private int rom;
}
