package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.domain.Tag;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.TagDTO;
import com.sbaldass.movies_backend.repositories.MovieRepository;
import com.sbaldass.movies_backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagDTO> findAll() {
        return tagRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag addTag(Tag tag) {
        tag.setCreatedAt(LocalDate.now());
        tag.setUpdatedAt(LocalDate.now());
        return tagRepository.save(tag);
    }

    public Tag updateTag(TagDTO tagDTO, Long id) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        existingTag.setUserId(tagDTO.getUserId());
        existingTag.setCreatedAt(LocalDate.now());
        existingTag.setName(tagDTO.getName());
        existingTag.setUpdatedAt(LocalDate.now());
        return tagRepository.save(existingTag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    private TagDTO convertToDTO(Tag movieTag){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(movieTag.getId());
        tagDTO.setCreatedAt(movieTag.getCreatedAt());
        tagDTO.setUpdatedAt(movieTag.getUpdatedAt());
        tagDTO.setName(movieTag.getName());
        return tagDTO;
    }

    private Tag convertToEntity(TagDTO movieTag){
        Tag tagDTO = new Tag();
        tagDTO.setId(movieTag.getId());
        tagDTO.setCreatedAt(movieTag.getCreatedAt());
        tagDTO.setUpdatedAt(movieTag.getUpdatedAt());
        tagDTO.setName(movieTag.getName());
        return tagDTO;
    }

}
