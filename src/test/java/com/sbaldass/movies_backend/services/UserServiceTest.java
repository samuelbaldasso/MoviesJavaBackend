package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Role;
import com.sbaldass.movies_backend.domain.User;
import com.sbaldass.movies_backend.dtos.UserDTO;
import com.sbaldass.movies_backend.repositories.RoleRepository;
import com.sbaldass.movies_backend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setAvatar("avatar.jpg");
        userDto.setAdmin(true);

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAvatar(userDto.getAvatar());
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        user.setRoles(Collections.singletonList(role));

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(userDto);

        assertEquals(user, result);
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAlterUser() {
        Long id = 1L;
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setAvatar("avatar.jpg");
        userDto.setCreatedAt(LocalDate.now());
        userDto.setUpdatedAt(LocalDate.now());
        userDto.setAdmin(true);

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));

        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setUsername("existingUser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword(passwordEncoder.encode("existingPassword"));
        existingUser.setAvatar("existingAvatar.jpg");
        existingUser.setCreatedAt(LocalDate.now());
        existingUser.setUpdatedAt(LocalDate.now());
        existingUser.setRoles(Collections.singletonList(role));

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAvatar(userDto.getAvatar());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setRoles(Collections.singletonList(role));

        when(userRepository.save(existingUser)).thenReturn(user);

        User result = userService.alterUser(userDto, id);

        assertEquals(user, result);
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testFindUserById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setAvatar("avatar.jpg");
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserById(id);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.findAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = 1L;

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}