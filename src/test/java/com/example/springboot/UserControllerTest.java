package com.example.springboot;

import com.example.springboot.controllers.UserController;
import com.example.springboot.models.User;
import com.example.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsers_ReturnsListOfUsers() {
        // Arrange
        User user1 = new User("username1", "email1");
        User user2 = new User("username2", "email2");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userController.getAllUsers();

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(userService, times(1)).getAllUsers();
    }


    @Test
    public void createUser_ValidUser_ReturnsCreatedUser() {
        // Arrange
        User user = new User("username", "email");
        User expectedUser = new User("username", "email");

        when(userService.createUser(user)).thenReturn(expectedUser);

        // Act
        User actualUser = userController.createUser(user);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void updateUser_ExistingUserIdAndValidUser_ReturnsUpdatedUser() {
        // Arrange
        Long userId = 1L;
        User updatedUser = new User("updatedUsername", "updatedEmail");
        User expectedUser = new User("updatedUsername", "updatedEmail");

        when(userService.updateUser(userId, updatedUser)).thenReturn(expectedUser);

        // Act
        User actualUser = userController.updateUser(userId, updatedUser);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    
}