package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.domain.Tag;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.TagDTO;
import com.sbaldass.movies_backend.services.MovieService;
import com.sbaldass.movies_backend.services.TagService;
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
import static org.mockito.Mockito.*;

public class TagControllerTest {
    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        List<TagDTO> tagDTOs = new ArrayList<>();
        when(tagService.findAll()).thenReturn(tagDTOs);

        List<TagDTO> result = tagController.getAll();

        assertEquals(tagDTOs, result);
    }

    @Test
    public void testGetById() {
        Tag tag = mock(Tag.class);
        Optional<Tag> optional = Optional.of(tag);
        when(tagService.findById(1L)).thenReturn(optional);

        ResponseEntity<Tag> result = tagController.getTagById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(tag, result.getBody());
    }

    @Test
    public void testGetById_notFound() {
        when(tagService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Tag> result = tagController.getTagById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testAdd() {
        Tag tag = mock(Tag.class);
        when(tagService.addTag(tag)).thenReturn(tag);

        Tag result = tagController.addTag(tag);

        assertEquals(tag, result);
    }

    @Test
    public void testUpdate() {
        TagDTO tagDTO = mock(TagDTO.class);
        Tag movie = mock(Tag.class);
        when(tagService.updateTag(tagDTO, 1L)).thenReturn(movie);

        Tag result = tagController.updateTag(tagDTO, 1L);

        assertEquals(movie, result);
    }

    @Test
    public void testDelete() {
        tagController.deleteTag(1L);

        verify(tagService).deleteTag(1L);
    }
}
