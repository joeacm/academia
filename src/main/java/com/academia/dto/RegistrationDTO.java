package com.academia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {

    private Integer id;
    @NotNull
    private LocalDateTime dateTimeRegistration;
    @NotNull
    private StudentDTO student;
    @NotNull
    @NotEmpty
    @JsonManagedReference
    private List<Registration_DetailDTO> detailsRegistration;
    @NotNull
    private boolean enabledRegistration;
}
