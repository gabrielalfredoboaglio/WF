package com.C10G14.WorldFitBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Exercises")
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TITLE",
            unique = true)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MEDIA")
    private String media;


    @ManyToOne(fetch = FetchType.EAGER,
               cascade = CascadeType.PERSIST)
    @JoinColumn(name = "EXERCISE_UNIT")
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @JsonBackReference
    @OneToMany(
            mappedBy = "exercise"
    )
    private Set<Exercise_Routine> routines;

    public Exercise(String title, String description, String media, Unit unit) {
        this.title = title;
        this.description = description;
        this.media = media;
        this.unit = unit;
        this.routines = new LinkedHashSet<>();
    }
    public Exercise(Long id, String title, String media, Unit unit) {
        this.id = id;
        this.title = title;
        this.media = media;
        this.unit = unit;
        this.routines = new LinkedHashSet<>();
    }

}
