package com.sbaldass.movies_backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long movieId;
    private Long userId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
