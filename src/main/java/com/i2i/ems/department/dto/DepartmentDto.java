package com.i2i.ems.department.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 *     Defines blueprint for Department entity for creation operation,
 *     input should be given in this defined format.
 *     such as id, name.
 * </p>
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotBlank(message = "should contain alphabets")
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Name should be alphabets" )
    private String name;
    private List<String> employeeNames;
}
