package com.academia.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Registration_DetailDTO {

    @NotNull
    @NotEmpty
    @JsonBackReference
    private RegistrationDTO registration;
    @NotNull
    @NotEmpty
    private CourseDTO course;
    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String classroomRegistration;
}
