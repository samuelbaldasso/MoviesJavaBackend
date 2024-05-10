package com.sbaldass.movies_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private double rating;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
