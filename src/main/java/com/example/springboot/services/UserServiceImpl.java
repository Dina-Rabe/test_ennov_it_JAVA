package com.example.springboot.services;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements IUserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
}
