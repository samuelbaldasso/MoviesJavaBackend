package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.models.Cast;
import com.sbaldass.movies_backend.models.Movie;
import com.sbaldass.movies_backend.models.Tag;
import com.sbaldass.movies_backend.repositories.CastRepository;
import com.sbaldass.movies_backend.repositories.MovieRepository;
import com.sbaldass.movies_backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CastRepository castRepository;

    public Movie create(MovieDTO movie){
        Movie movie1 = new Movie();
        movie1.setDescription(movie.getDescription());
        movie1.setTitle(movie.getTitle());
        movie1.setRating(movie.getRating());

        Tag tag = tagRepository.findById(movie.getTagId()).orElseThrow(() ->
                new RuntimeException("Tag do filme não encontrado."));;
        movie1.setTag(tag);

        List<Cast> cast = castRepository.findAllById(movie.getCastId());
        movie1.setCasting(cast);

        return movieRepository.save(movie1);
    }

    public Movie update(MovieDTO movie, Long id){
        Movie movie1 = getById(id);
        movie1.setDescription(movie.getDescription());
        movie1.setTitle(movie.getTitle());
        movie1.setRating(movie.getRating());

        Tag tag = tagRepository.findById(movie.getTagId()).orElseThrow(() ->
                new RuntimeException("Tag do filme não encontrado."));;
        movie1.setTag(tag);

        List<Cast> cast = castRepository.findAllById(movie.getCastId());
        movie1.setCasting(cast);

        return movieRepository.save(movie1);
    }

    public Movie getById(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Filme não encontrado."));
    }

    public List<Movie> movieList(){
        return movieRepository.findAll();
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

}
