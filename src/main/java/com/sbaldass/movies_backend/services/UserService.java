package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Role;
import com.sbaldass.movies_backend.domain.User;
import com.sbaldass.movies_backend.dtos.UserDTO;
import com.sbaldass.movies_backend.repositories.RoleRepository;
import com.sbaldass.movies_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAvatar(userDto.getAvatar());
        user.setCreatedAt(LocalDate.now());

        Role userRole = roleRepository.findByName(userDto.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found."));
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }


    public User alterUser(UserDTO userDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        existingUser.setAvatar(userDto.getAvatar());
        existingUser.setCreatedAt(userDto.getCreatedAt());
        existingUser.setUpdatedAt(LocalDate.now());
        return userRepository.save(existingUser);
    }


    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public List<UserDTO> findAllUsers() throws Exception{
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User usuario){
        UserDTO usuarioDTO = new UserDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setAvatar(usuario.getAvatar());
        usuarioDTO.setCreatedAt(usuario.getCreatedAt());
        usuarioDTO.setUpdatedAt(usuario.getUpdatedAt());
        return usuarioDTO;
    }

    private User convertToEntity(UserDTO usuario){
        User usuarioDTO = new User();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setAvatar(usuario.getAvatar());
        usuarioDTO.setCreatedAt(usuario.getCreatedAt());
        usuarioDTO.setUpdatedAt(usuario.getUpdatedAt());
        return usuarioDTO;
    }

    public void deleteUser(Long id) throws Exception{
        userRepository.deleteById(id);
    }

}
