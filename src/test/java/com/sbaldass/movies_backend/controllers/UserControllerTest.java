package com.sbaldass.movies_backend.controllers;

import com.sbaldass.movies_backend.domain.Movie;
import com.sbaldass.movies_backend.domain.User;
import com.sbaldass.movies_backend.dtos.MovieDTO;
import com.sbaldass.movies_backend.dtos.UserDTO;
import com.sbaldass.movies_backend.services.MovieService;
import com.sbaldass.movies_backend.services.UserService;
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

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserDTO> userDTOs = new ArrayList<>();
        when(userService.findAllUsers()).thenReturn(userDTOs);

        List<UserDTO> result = userController.getAll();

        assertEquals(userDTOs, result);
    }

    @Test
    public void testGetById() throws Exception {
        User user = mock(User.class);
        Optional<User> optional = Optional.of(user);
        when(userService.findUserById(1L)).thenReturn(optional);

        ResponseEntity<User> result = userController.getById(1L);

        assertEquals(user, result.getBody());
    }

    @Test
    public void testGetById_notFound() throws Exception {
        when(userService.findUserById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> result = userController.getById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testAdd() throws Exception {
        User user = mock(User.class);
        UserDTO userDTO = mock(UserDTO.class);
        when(userService.saveUser(userDTO)).thenReturn(user);

        User result = userController.create(userDTO);

        assertEquals(user, result);
    }

    @Test
    public void testUpdate() throws Exception {
        UserDTO userDTO = mock(UserDTO.class);
        User user = mock(User.class);
        when(userService.alterUser(userDTO, 1L)).thenReturn(user);

        User result = userController.putUser(1L, userDTO);

        assertEquals(user, result);
    }

    @Test
    public void testDelete() throws Exception {
        userController.delete(1L);

        verify(userService).deleteUser(1L);
    }
}
