package com.example.springboot.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Ticket;
import com.example.springboot.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
    
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable("id") Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }
    
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
    
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable("id") Long ticketId, @RequestBody Ticket updatedTicket) {
        return ticketService.updateTicket(ticketId, updatedTicket);
    }
    
    @PutMapping("/{id}/assign/{userId}")
    public Ticket assignTicketToUser(@PathVariable("id") Long ticketId, @PathVariable("userId") Long userId) {
        return ticketService.assignTicketToUser(ticketId, userId);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") Long ticketId) {
        ticketService.deleteTicket(ticketId);
    }
    
    // Autres méthodes et points de terminaison
    
}