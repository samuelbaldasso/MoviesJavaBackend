package com.sbaldass.movies_backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cast> casting = new ArrayList<>();

    private double rating;

    @OneToOne
    private Tag tag;
}
