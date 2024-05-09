package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.models.Movie;
import com.sbaldass.movies_backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/movie")
@RestController
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> create(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.create(movieDTO);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> update(@RequestBody MovieDTO movieDTO, @PathVariable Long id) {
        Movie movie = movieService.update(movieDTO, id);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = movieService.movieList();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Movie movie = movieService.getById(id);

        return ResponseEntity.ok(movie);
    }

}
