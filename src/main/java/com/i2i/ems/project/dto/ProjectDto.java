package com.i2i.ems.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 *     Defines blueprint for Project entity for creation operation,
 *     input should be given in this defined format.
 *     such as id, name, duration.
 * </p>
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank(message = "should contain alphabets")
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Name should be alphabets" )
    private String name;
    @NotNull(message = "should contain digits")
    private int duration;
}
