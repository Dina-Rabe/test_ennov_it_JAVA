package com.example.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTicketsByUserId'");
    }

    public User createUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    public User updateUser(Long userId, User updatedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    // Méthodes de service pour les opérations CRUD sur les utilisateurs
}