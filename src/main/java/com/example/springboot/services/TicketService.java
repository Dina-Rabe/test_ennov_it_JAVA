package com.example.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;
import com.example.springboot.repositories.TicketRepository;
import com.example.springboot.repositories.UserRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long ticketId) {
        @SuppressWarnings("null")
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        }
        throw new IllegalArgumentException("Ticket not found for ticketId: " + ticketId);
    }

    public Ticket updateTicket(Long ticketId, Ticket updatedTicket) {
        @SuppressWarnings("null")
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setTitle(updatedTicket.getTitle());
            ticket.setDescription(updatedTicket.getDescription());
           
            return ticketRepository.save(ticket);
        }
        throw new IllegalArgumentException("Ticket not found for ticketId: " + ticketId);
    }

    public Ticket assignTicketToUser(Long ticketId, Long userId) {
        @SuppressWarnings("null")
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        @SuppressWarnings("null")
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTicket.isPresent() && optionalUser.isPresent()) {
            Ticket ticket = optionalTicket.get();
            User user = optionalUser.get();
            ticket.setAssignedUser(user);;
            return ticketRepository.save(ticket);
        }
        throw new IllegalArgumentException("Ticket or User not found for ticketId: " + ticketId + ", userId: " + userId);
    }

    @SuppressWarnings("null")
    public Ticket deleteTicket(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
            return ticket;
        }
        throw new IllegalArgumentException("Ticket not found for ticketId: " + ticketId);
    }

    @SuppressWarnings("null")
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

   
}