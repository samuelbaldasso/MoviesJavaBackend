package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        List<MovieDTO> movieDTOs = new ArrayList<>();
        when(movieService.findAllMovies()).thenReturn(movieDTOs);

        List<MovieDTO> result = movieController.getAllMovies();

        assertEquals(movieDTOs, result);
    }

    @Test
    public void testGetMovieById() {
        Movie movie = mock(Movie.class);
        Optional<Movie> optionalMovie = Optional.of(movie);
        when(movieService.findMovieById(1L)).thenReturn(optionalMovie);

        ResponseEntity<Movie> result = movieController.getMovieById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(movie, result.getBody());
    }

    @Test
    public void testGetMovieById_notFound() {
        when(movieService.findMovieById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Movie> result = movieController.getMovieById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testAddMovie() {
        Movie movie = mock(Movie.class);
        when(movieService.addMovie(movie)).thenReturn(movie);

        Movie result = movieController.addMovie(movie);

        assertEquals(movie, result);
    }

    @Test
    public void testUpdateMovie() {
        MovieDTO movieDTO = mock(MovieDTO.class);
        Movie movie = mock(Movie.class);
        when(movieService.updateMovie(movieDTO, 1L)).thenReturn(movie);

        Movie result = movieController.updateMovie(movieDTO, 1L);

        assertEquals(movie, result);
    }

    @Test
    public void testDeleteMovie() {
        movieController.deleteMovie(1L);

        verify(movieService).deleteMovie(1L);
    }
}