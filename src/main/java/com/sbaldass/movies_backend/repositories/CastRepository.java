package com.sbaldass.movies_backend.repositories;

import com.sbaldass.movies_backend.models.Cast;
import com.sbaldass.movies_backend.models.CastType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CastRepository extends JpaRepository<Cast, Long> {

}
