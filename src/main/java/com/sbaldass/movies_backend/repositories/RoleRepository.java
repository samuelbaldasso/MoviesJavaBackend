package com.sbaldass.movies_backend.repositories;

import com.sbaldass.movies_backend.models.Role;
import com.sbaldass.movies_backend.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
