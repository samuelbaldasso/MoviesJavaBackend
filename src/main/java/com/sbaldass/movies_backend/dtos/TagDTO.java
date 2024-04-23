package com.sbaldass.movies_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TagDTO {
    private Long id;
    private String name;
    private Long movieId;
    private Long userId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public TagDTO() {

    }
}
