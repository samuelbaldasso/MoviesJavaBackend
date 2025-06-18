package com.sbaldass.movies_backend.services;

import com.sbaldass.movies_backend.domain.Role;
import com.sbaldass.movies_backend.domain.User;
import com.sbaldass.movies_backend.dtos.UserDTO;
import com.sbaldass.movies_backend.repositories.RoleRepository;
import com.sbaldass.movies_backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Nested
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDto;
    private Role adminRole;

    @BeforeEach
    void setUp() {
        // Common setup for DTO
        userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setAvatar("avatar.jpg");
        userDto.setAdmin(true);

        // Common setup for Role
        adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setName("ROLE_ADMIN");

        // Common setup for User entity
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("hashedPassword"); // Assume it's already hashed
        user.setAvatar("avatar.jpg");
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        user.setRoles(Collections.singletonList(adminRole));
    }


    @Test
    void testSaveUser() {
        // Arrange
        // We want to capture the user object that the service creates and saves
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        // Mock what happens inside the service
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(adminRole));
        // Use the captor to save the user, then return it
        when(userRepository.save(userArgumentCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.saveUser(userDto);

        // Assert
        assertNotNull(result);
        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals("hashedPassword", result.getPassword()); // Check the hashed password
        assertTrue(result.getRoles().contains(adminRole));

        // Verify that the mocks were called
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("password");
    }

    @Test
    void testAlterUser() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User(); // A simple existing user
        existingUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(adminRole));
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("aNewHashedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.alterUser(userDto, userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals("aNewHashedPassword", result.getPassword());
        assertTrue(result.getRoles().contains(adminRole));

        // Verify
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(existingUser);
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
    }

    @Test
    void testFindUserById() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findUserById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllUsers() throws Exception {
        // Arrange
        // Create a realistic Role object that would exist for a normal user
        Role userRole = new Role();
        userRole.setId(2L);
        userRole.setName("ROLE_USER");

        // Create realistic User objects that have a list of roles, preventing the crash
        User user1 = new User();
        user1.setId(10L);
        user1.setRoles(List.of(userRole));

        User user2 = new User();
        user2.setId(11L);
        user2.setRoles(List.of(userRole));

        List<User> users = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDTO> result = userService.findAllUsers();

        // Assert
        // The service should now correctly map the 2 User entities to 2 UserDTOs
        assertEquals(users.size(), result.size());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    void testDeleteUser() throws Exception {
        // Arrange
        Long id = 1L;
        doNothing().when(userRepository).deleteById(id); // Standard practice for void methods

        // Act
        userService.deleteUser(id);

        // Assert / Verify
        verify(userRepository, times(1)).deleteById(id);
    }
}