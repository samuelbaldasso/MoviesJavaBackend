package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.domain.Tag;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.TagDTO;
import com.sbaldass.movies_backend.services.MovieService;
import com.sbaldass.movies_backend.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDTO> getAll() {
        return tagService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return tagService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tag addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }

    @PutMapping("/{id}")
    public Tag updateTag(@RequestBody TagDTO tagDTO, @PathVariable Long id) {
        return tagService.updateTag(tagDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}

