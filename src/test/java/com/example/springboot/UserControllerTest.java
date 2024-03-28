package com.example.springboot;

import com.example.springboot.controllers.UserController;
import com.example.springboot.models.User;
import com.example.springboot.services.IUserService;
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
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ValidUser_ReturnsCreatedUser() {
        // Arrange
        User user = User.builder().email("user1@email.com").username("username1").build();

        when(userService.save(user)).thenReturn(user);

        // Act
        User actualUser = userController.createUser(user);

        // Assert
        assertEquals(user, actualUser);
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void updateUser_ExistingUserIdAndValidUser_ReturnsUpdatedUser() {
        // Arrange
        Long userId = 1L;
        User expectedUser = User.builder().email("user2@email.com").username("username2").build();
        when(userService.update(anyLong(), any(User.class))).thenReturn(expectedUser);

        // Act
        User actualUser = userController.updateUser(userId, User.builder().email("user1@email.com").username("username1").build());

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userService, times(1)).update(anyLong(), any(User.class));
    }

    
}
