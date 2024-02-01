package com.academia.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCourse;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 5, nullable = false)
    private String acronym;

    @Column(nullable = false)
    private boolean enabled;
}
