package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Tag;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.TagDTO;
import com.sbaldass.movies_backend.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    @Test
    public void testSave() {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName("Adventure");
        tagDTO.setMovieId(1L);
        tagDTO.setUserId(1L);
        tagDTO.setUpdatedAt(LocalDate.now());
        tagDTO.setCreatedAt(LocalDate.now());

        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        tag.setMovieId(tagDTO.getMovieId());
        tag.setUserId(tagDTO.getUserId());
        tag.setUpdatedAt(tagDTO.getUpdatedAt());
        tag.setCreatedAt(tagDTO.getCreatedAt());

        when(tagRepository.save(tag)).thenReturn(tag);

        Tag result = tagService.addTag(tag);

        assertEquals(tag, result);
        verify(tagRepository, times(1)).save(tag);
    }

    @Test
    public void testAlter() {
        Long id = 1L;

        TagDTO tagDTO = new TagDTO();
        tagDTO.setName("Action");
        tagDTO.setMovieId(1L);
        tagDTO.setUserId(1L);
        tagDTO.setUpdatedAt(LocalDate.now());
        tagDTO.setCreatedAt(LocalDate.now());

        Tag existingTag = new Tag();
        existingTag.setName("Adventure");
        existingTag.setMovieId(1L);
        existingTag.setUserId(1L);
        existingTag.setUpdatedAt(LocalDate.now());
        existingTag.setCreatedAt(LocalDate.now());

        when(tagRepository.findById(id)).thenReturn(Optional.of(existingTag));

        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        tag.setMovieId(tagDTO.getMovieId());
        tag.setUserId(tagDTO.getUserId());
        tag.setUpdatedAt(tagDTO.getUpdatedAt());
        tag.setCreatedAt(tagDTO.getCreatedAt());

        when(tagRepository.save(existingTag)).thenReturn(tag);

        Tag result = tagService.updateTag(tagDTO, id);

        assertEquals(tag, result);
        verify(tagRepository, times(1)).findById(id);
        verify(tagRepository, times(1)).save(existingTag);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Tag existingTag = new Tag();
        existingTag.setName("Adventure");
        existingTag.setMovieId(1L);
        existingTag.setUserId(1L);
        existingTag.setUpdatedAt(LocalDate.now());
        existingTag.setCreatedAt(LocalDate.now());

        when(tagRepository.findById(id)).thenReturn(Optional.of(existingTag));

        Optional<Tag> result = tagService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(existingTag, result.get());
        verify(tagRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag());
        tags.add(new Tag());

        when(tagRepository.findAll()).thenReturn(tags);

        List<TagDTO> result = tagService.findAll();

        assertEquals(2, result.size());
        verify(tagRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;

        tagService.deleteTag(id);

        verify(tagRepository, times(1)).deleteById(id);
    }
}