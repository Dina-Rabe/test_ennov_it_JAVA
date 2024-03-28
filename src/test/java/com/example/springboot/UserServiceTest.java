package com.example.springboot;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.TicketStatus;
import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTicketsByUserId_ExistingUserId_ReturnsListOfTickets() {
        // Arrange
        Long userId = 1L;
        User user = new User("username", "email");
        Ticket ticket1 = new Ticket("Title 1", "Description 1", null, TicketStatus.OUVERT);
        Ticket ticket2 = new Ticket("Title 2", "Description 2", null, TicketStatus.OUVERT);
        user.setAssignedTickets(Arrays.asList(ticket1, ticket2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        List<Ticket> actualTickets = userService.getTicketsByUserId(userId);

        // Assert
        assertEquals(user.getAssignedTickets(), actualTickets);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void getTicketsByUserId_NonExistingUserId_ThrowsIllegalArgumentException() {
        // Arrange
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getTicketsByUserId(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void createUser_ValidUser_ReturnsCreatedUser() {
        // Arrange
        User user = new User("username", "email");
        User expectedUser = new User("username", "email");

        when(userRepository.save(user)).thenReturn(expectedUser);

        // Act
        User actualUser = userService.createUser(user);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateUser_ExistingUserIdAndValidUser_ReturnsUpdatedUser() {
        // Arrange
        Long userId = 1L;
        User user = new User("username", "email");
        User updatedUser = new User("updatedUsername", "updatedEmail");
        User expectedUser = new User("updatedUsername", "updatedEmail");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(expectedUser);

        // Act
        User actualUser = userService.updateUser(userId, updatedUser);

        // Assert
        assertEquals(expectedUser, actualUser);
        assertEquals(updatedUser.getUsername(), actualUser.getUsername());
        assertEquals(updatedUser.getEmail(), actualUser.getEmail());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @SuppressWarnings("null")
    @Test
    public void updateUser_NonExistingUserId_ThrowsIllegalArgumentException() {
        // Arrange
        Long userId = 1L;
        User updatedUser = new User("updatedUsername", "updatedEmail");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, updatedUser));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any());
    }

    
}
