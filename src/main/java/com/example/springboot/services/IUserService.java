package com.example.springboot.services;

import com.example.springboot.dto.UserDto;

import java.util.List;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;

public interface IUserService extends GenericService<User, Long> {

    UserDto createUser(UserDto dto) ;

    List<Ticket> listTicketAssigned(UserDto dto);

    UserDto updateUser(Long userId, UserDto updatedUser);
}
