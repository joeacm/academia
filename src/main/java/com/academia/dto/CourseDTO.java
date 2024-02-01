package com.academia.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String nameCourse;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 5)
    private String acronymCourse;
    @NotNull
    private boolean enabledCourse;
}
