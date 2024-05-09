package com.sbaldass.movies_backend.dtos;

import com.sbaldass.movies_backend.models.Cast;
import com.sbaldass.movies_backend.models.CastType;
import com.sbaldass.movies_backend.models.Tag;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private Long tagId;
    private List<Long> castId;
    private String description;
    private double rating;
}
