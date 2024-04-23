package com.sbaldass.movies_backend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private double nota;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
