package com.sbaldass.movies_backend.dtos;

import com.sbaldass.movies_backend.models.CastType;
import lombok.Data;

import java.util.List;

@Data
public class CastDTO {
    private Long id;
    private CastType filmRole;
    private String name;
    private String age;
    private String description;
}
