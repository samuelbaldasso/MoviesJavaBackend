package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.dtos.CastDTO;
import com.sbaldass.movies_backend.models.Cast;
import com.sbaldass.movies_backend.services.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/casting")
@RestController
public class CastController {
    private CastService castService;

    @Autowired
    public CastController(CastService castService) {
        this.castService = castService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cast> create(@RequestBody CastDTO cast) {
        return ResponseEntity.ok(castService.create(cast));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cast> update(@RequestBody CastDTO cast, @PathVariable Long id) {
        return ResponseEntity.ok(castService.update(cast, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        castService.delete(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Cast>> getAll() {
        List<Cast> casting = castService.getAll();

        return ResponseEntity.ok(casting);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Cast> getById(@PathVariable Long id) {
        return ResponseEntity.ok(castService.getById(id));
    }

}
