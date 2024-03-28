package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;

import com.example.springboot.dto.TicketDto;
import com.example.springboot.services.ITicketService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Ticket;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    @GetMapping("/")
	public String index() {
		return " POINS TICKET!";
	}

    private final ITicketService iTicketService;

    public TicketController(ITicketService iTicketService) {
        this.iTicketService = iTicketService;
    }

    @GetMapping("{id}/tickets")
    public List<TicketDto> fetchAll(@PathVariable("id") Long userId) {
        return iTicketService.getAllTickets(userId);
    }

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable("id") Long ticketId) {
        Optional<Ticket> ticketOptional = iTicketService.findById(ticketId);
        if (ticketOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        return ticketOptional.get().ticketDto();
    }
    
    @PostMapping
    public TicketDto createTicket(@Validated @RequestBody TicketDto ticket) {
        return iTicketService.createTicket(ticket);
    }
    
    @PutMapping("/{id}")
    public TicketDto updateTicket(@PathVariable("id") Long ticketId,
                                  @Validated @RequestBody TicketDto updatedTicket) {
        return iTicketService.updateTicket(ticketId, updatedTicket);
    }
    
    @PutMapping("/{id}/assign/{userId}")
    public TicketDto assignTicketToUser(@PathVariable("id") Long ticketId, @PathVariable("userId") Long userId) {
        return iTicketService.assignTicketToUser(ticketId, userId);
    }
    
    @DeleteMapping("/{id}")
    public Boolean deleteTicket(@PathVariable("id") Long ticketId) {
        return iTicketService.delete(ticketId);
    }
}
