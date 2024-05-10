package com.sbaldass.movies_backend.repositories;

import com.sbaldass.movies_backend.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
