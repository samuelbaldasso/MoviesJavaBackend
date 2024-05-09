package com.sbaldass.movies_backend.repositories;

import com.sbaldass.movies_backend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
