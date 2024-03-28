package com.example.springboot.controllers;

import com.example.springboot.models.User;
import com.example.springboot.services.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/")
	public String index() {
		return " POINS USER!";
	}

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return iUserService.findById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return iUserService.save(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long userId, @RequestBody User updatedUser) {
        return iUserService.update(userId, updatedUser);
    }
}
