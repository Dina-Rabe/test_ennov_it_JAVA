package com.example.springboot;

import com.example.springboot.dto.UserDto;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.IUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IUserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTicketsByUserId_NonExistingUserId_ThrowsIllegalArgumentException() {
        // Arrange
        Long userId = 1L;
        UserDto user = UserDto.builder().username("user1").email("user1@email.com").build();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.listTicketAssigned(user));
        verify(userRepository, times(1)).findById(userId);
    }

    @SuppressWarnings("null")
    @Test
    public void createUser_ValidUser_ReturnsCreatedUser() {
        // Arrange
        UserDto user = UserDto.builder().username("user1").email("user1@email.com").build();
        UserDto expectedUser = UserDto.builder().username("user1").email("user1@email.com").build();

        when(userRepository.save(user.toEntity())).thenReturn(expectedUser.toEntity());

        // Act
        UserDto actualUser = userService.createUser(user);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).save(user.toEntity());
    }

    @SuppressWarnings("null")
    @Test
    public void updateUser_ExistingUserIdAndValidUser_ReturnsUpdatedUser() {
        // Arrange
        Long userId = 1L;
        UserDto user = UserDto.builder().username("user1").email("user1@email.com").build();
        UserDto updatedUser = UserDto.builder().username("user1Updated").email("user1.updated@email.com").build();
        UserDto expectedUser = UserDto.builder().username("user1Updated").email("user1.updated@email.com").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user.toEntity()));
        when(userRepository.save(user.toEntity())).thenReturn(expectedUser.toEntity());

        // Act
        UserDto actualUser = userService.updateUser(userId, updatedUser);

        // Assert
        assertEquals(expectedUser, actualUser);
        assertEquals(updatedUser.getUsername(), actualUser.getUsername());
        assertEquals(updatedUser.getEmail(), actualUser.getEmail());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user.toEntity());
    }

    @SuppressWarnings("null")
    @Test
    public void updateUser_NonExistingUserId_ThrowsIllegalArgumentException() {
        // Arrange
        Long userId = 1L;
        UserDto updatedUser = UserDto.builder().username("user1").email("user1@email.com").build();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, updatedUser));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any());
    }

    
}
