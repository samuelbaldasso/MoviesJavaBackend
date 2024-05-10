package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.domain.Role;
import com.sbaldass.movies_backend.domain.User;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.UserDTO;
import com.sbaldass.movies_backend.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testSave() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setDescription("Filme de 2003");
        movieDTO.setNota(8);
        movieDTO.setTitle("Piratas do Caribe 1");
        movieDTO.setUpdatedAt(LocalDate.now());
        movieDTO.setCreatedAt(LocalDate.now());
        movieDTO.setUserId(1L);

        Movie movie = new Movie();
        movie.setDescription(movieDTO.getDescription());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        movie.setTitle(movieDTO.getTitle());
        movie.setNota(movieDTO.getNota());
        movie.setUserId(movieDTO.getUserId());

        when(movieRepository.save(movie)).thenReturn(movie);

        Movie result = movieService.addMovie(movie);

        assertEquals(movie, result);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void testAlter() {
        Long id = 1L;

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setDescription("Filme de 2003");
        movieDTO.setNota(8);
        movieDTO.setTitle("Piratas do Caribe 1");
        movieDTO.setUpdatedAt(LocalDate.now());
        movieDTO.setCreatedAt(LocalDate.now());
        movieDTO.setUserId(1L);

        Movie existingMovie = new Movie();
        existingMovie.setDescription("Filme de 2004");
        existingMovie.setNota(8.9);
        existingMovie.setTitle("A Ilha");
        existingMovie.setUpdatedAt(LocalDate.now());
        existingMovie.setCreatedAt(LocalDate.now());
        existingMovie.setUserId(1L);

        when(movieRepository.findById(id)).thenReturn(Optional.of(existingMovie));

        Movie movie = new Movie();
        movie.setDescription(movieDTO.getDescription());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        movie.setTitle(movieDTO.getTitle());
        movie.setNota(movieDTO.getNota());
        movie.setUserId(movieDTO.getUserId());

        when(movieRepository.save(existingMovie)).thenReturn(movie);

        Movie result = movieService.updateMovie(movieDTO, id);

        assertEquals(movie, result);
        verify(movieRepository, times(1)).findById(id);
        verify(movieRepository, times(1)).save(existingMovie);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Movie existingMovie = new Movie();
        existingMovie.setDescription("Filme de 2004");
        existingMovie.setNota(8.9);
        existingMovie.setTitle("A Ilha");
        existingMovie.setUpdatedAt(LocalDate.now());
        existingMovie.setCreatedAt(LocalDate.now());
        existingMovie.setUserId(1L);

        when(movieRepository.findById(id)).thenReturn(Optional.of(existingMovie));

        Optional<Movie> result = movieService.findMovieById(id);

        assertTrue(result.isPresent());
        assertEquals(existingMovie, result.get());
        verify(movieRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        when(movieRepository.findAll()).thenReturn(movies);

        List<MovieDTO> result = movieService.findAllMovies();

        assertEquals(2, result.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;

        movieService.deleteMovie(id);

        verify(movieRepository, times(1)).deleteById(id);
    }
}