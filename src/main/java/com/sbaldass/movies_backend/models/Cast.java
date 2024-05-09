package com.sbaldass.movies_backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "casting")
@Data
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CastType filmRole;

    private String name;
    private String age;
    private String description;
}
