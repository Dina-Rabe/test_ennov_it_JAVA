package com.example.springboot.services;

import com.example.springboot.dto.TicketDto;
import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;
import com.example.springboot.repositories.TicketRepository;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl extends GenericServiceImpl<Ticket, Long> implements ITicketService {

    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository repository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @Override
    public TicketDto createTicket(TicketDto dto) {
        Assert.notNull(dto, "TicketDto is mandatory");
        return save(dto.toEntity()).ticketDto();
    }

    @Override
    public TicketDto assignTicketToUser(Long ticketId, Long userId) {
        Assert.notNull(ticketId, "Ticket::id is mandatory");
        Assert.notNull(userId, "User::id is mandatory");
        @SuppressWarnings("null")
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user found with the given id " + userId);
        }

        Ticket ticket = findById(ticketId).orElseThrow(() -> new IllegalArgumentException("No Ticket found with the given id " + ticketId));
        User user = userOptional.get();
        ticket.setAssignedUser(user);

        return save(ticket).ticketDto();
    }

    @Override
    public List<TicketDto> getAllTickets(Long userId) {
        Assert.notNull(userId, "ID is mandatory");

        @SuppressWarnings("null")
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            throw new IllegalArgumentException("No user found with the given id " + userId);
        }

        return ((TicketRepository) repository).findAllByAssignedUser(userById.get()).stream().map(Ticket::ticketDto).collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    @Override
    public TicketDto updateTicket(Long ticketId, TicketDto dto) {
        Assert.notNull(ticketId, "ID is mandatory");
        Assert.notNull(dto, "TicketDto is mandatory");

        Optional<Ticket> ticketOptional = findById(ticketId);
        if (ticketOptional.isEmpty()) {
            throw new IllegalArgumentException("No Ticket found with the given id " + ticketId);
        }

        Ticket ticket = ticketOptional.get();
        BeanUtils.copyProperties(dto, ticket, "id", "assignedUser");

        return save(ticket).ticketDto();
    }
}
