package com.example.springboot.repositories;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springboot.models.User;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {
   
    @SuppressWarnings("null")
    List<User> findAll();
}
