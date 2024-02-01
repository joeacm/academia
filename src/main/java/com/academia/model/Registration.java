package com.academia.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRegister;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Registration_Detail> details;

    @Column(nullable = false)
    private boolean enabled;
}
