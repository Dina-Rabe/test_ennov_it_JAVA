package com.example.springboot.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot.models.Ticket;
import com.example.springboot.repositories.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTickets'");
    }

    public Ticket getTicketById(Long ticketId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTicketById'");
    }

    public Ticket updateTicket(Long ticketId, Ticket updatedTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTicket'");
    }

    public Ticket assignTicketToUser(Long ticketId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignTicketToUser'");
    }

    public Ticket deleteTicket(Long ticketId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTicket'");
    }

    public Ticket createTicket(Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTicket'");
    }

    // Méthodes de service pour les opérations CRUD sur les tickets
}