package com.example.springboot.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;
import com.example.springboot.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/")
	public String index() {
		return " POINS USER!";
	}

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/{id}/ticket")
    public List<Ticket> getTicketsByUserId(@PathVariable("id") Long userId) {
        return userService.getTicketsByUserId(userId);
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }
    
    // Autres méthodes et points de terminaison
    
}