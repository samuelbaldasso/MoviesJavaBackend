package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.dtos.TagDTO;
import com.sbaldass.movies_backend.models.Tag;
import com.sbaldass.movies_backend.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tag")
@RestController
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tag> create(@RequestBody TagDTO tag) {
        return ResponseEntity.ok(tagService.create(tag));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAll() {
        tagService.deleteAll();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Tag>> getAll() {
        List<Tag> tags = tagService.tagList();

        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Tag> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getById(id));
    }

}
