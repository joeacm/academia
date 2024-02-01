package com.academia.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String nameStudent;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String lastNameStudent;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String dniStudent;
    @Min(1)
    private int ageStudent;
}
