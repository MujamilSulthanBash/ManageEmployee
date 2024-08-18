package com.i2i.ems.employee.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Defines blueprint for Employee entity for creation operation,
 *     input should be given in this defined format.
 *     such as id, name, age, dateOfBirth, phoneNumber, email, departmentName, projectNames.
 * </p>
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Name should not blank")
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Name should be alphabets" )
    private String name;
    private String age;
    @Past(message = "Date should not be in future")
    private LocalDate dateOfBirth;
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}$", message = "should contain 10 digits")
    private String phoneNumber;
    @Email(message = "should contain @gmail.com")
    private String email;
    private String departmentName;
    private List<String> projectNames;
}
