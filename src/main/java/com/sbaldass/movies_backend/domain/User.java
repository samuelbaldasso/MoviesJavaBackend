package com.sbaldass.movies_backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Email
    private String email;
    private String password;

    @ManyToMany
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();

    private String avatar;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
