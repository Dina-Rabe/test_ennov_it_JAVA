package com.example.springboot.services;

import java.util.List;
import java.util.Optional;

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
        return userRepository.findAll();
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        @SuppressWarnings("null")
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getAssignedTickets();
        }
        throw new IllegalArgumentException("User not found for userId: " + userId);
    }

    @SuppressWarnings("null")
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        @SuppressWarnings("null")
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
           
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("User not found for userId: " + userId);
    }

   
}