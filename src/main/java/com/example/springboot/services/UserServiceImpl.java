package com.example.springboot.services;

import com.example.springboot.dto.UserDto;
import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements IUserService {

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto createUser(UserDto dto){
        Assert.notNull(dto, "UserDto is mandatory");
        return save(dto.toEntity()).userDto();
    }

    @Override
    public List<Ticket> listTicketAssigned(UserDto dto){
        Assert.notNull(dto, "UserDto is mandatory");
        return dto.getAssignedTickets();
    }

    @SuppressWarnings("null")
    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser){
        Assert.notNull(userId, "ID is mandatory");
        Assert.notNull(updatedUser, "updatedUser is mandatory");

        Optional<User> userOptional = findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No User found with the given id " + userId);
        }

        User u = userOptional.get();
        BeanUtils.copyProperties(updatedUser,u, "id", "assignedTickets");

        return save(u).userDto();
    }
}
