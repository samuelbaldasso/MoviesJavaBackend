package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.domain.User;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.UserDTO;
import com.sbaldass.movies_backend.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findAllMovies() {
        return movieRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        movie.setCreatedAt(LocalDate.now());
        movie.setUpdatedAt(LocalDate.now());
        return movieRepository.save(movie);
    }

    public Movie updateMovie(MovieDTO movieDTO, Long id) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        existingMovie.setUserId(movieDTO.getUserId());
        existingMovie.setCreatedAt(LocalDate.now());
        existingMovie.setDescription(movieDTO.getDescription());
        existingMovie.setRating(movieDTO.getRating());
        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setUpdatedAt(LocalDate.now());
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private MovieDTO convertToDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setCreatedAt(movie.getCreatedAt());
        movieDTO.setUpdatedAt(movie.getUpdatedAt());
        movieDTO.setRating(movieDTO.getRating());
        movieDTO.setTitle(movie.getTitle());
        return movieDTO;
    }
}
