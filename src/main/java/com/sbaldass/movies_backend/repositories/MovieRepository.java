package com.sbaldass.movies_backend.repositories;

import com.sbaldass.movies_backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
