package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.dtos.CastDTO;
import com.sbaldass.movies_backend.models.Cast;
import com.sbaldass.movies_backend.models.Movie;
import com.sbaldass.movies_backend.repositories.CastRepository;
import com.sbaldass.movies_backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CastService {
    @Autowired
    private CastRepository castRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Cast create(CastDTO casting) {
        Cast casting1 = new Cast();
        casting1.setDescription(casting.getDescription());
        casting1.setName(casting.getName());
        casting1.setFilmRole(casting.getFilmRole());
        casting1.setAge(casting.getAge());

        return castRepository.save(casting1);
    }

    public Cast update(CastDTO casting, Long id) {
        Cast casting1 = getById(id);

        casting1.setDescription(casting.getDescription());
        casting1.setName(casting.getName());
        casting1.setFilmRole(casting.getFilmRole());
        casting1.setAge(casting.getAge());

        return castRepository.save(casting1);
    }

    public List<Cast> getAll() {
        return castRepository.findAll();
    }

    public Cast getById(Long id) {
        return castRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Elenco do filme não encontrado."));
    }

    public void delete(Long id) {
        castRepository.deleteById(id);
    }
}
