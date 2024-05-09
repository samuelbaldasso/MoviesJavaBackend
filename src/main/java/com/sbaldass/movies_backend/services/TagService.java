package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.dtos.TagDTO;
import com.sbaldass.movies_backend.models.Movie;
import com.sbaldass.movies_backend.models.Tag;
import com.sbaldass.movies_backend.repositories.MovieRepository;
import com.sbaldass.movies_backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Tag create(TagDTO tag) {
        Tag tag1 = new Tag();
        tag1.setTitle(tag.getTitle());

        return tagRepository.save(tag1);
    }

    public Tag getById(Long id) {
        return tagRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Tag do filme não encontrado."));
    }

    public List<Tag> tagList() {
        return tagRepository.findAll();
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    public void deleteAll() {
        tagRepository.deleteAll();
    }
}
