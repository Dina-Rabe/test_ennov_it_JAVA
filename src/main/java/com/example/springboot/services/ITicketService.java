package com.example.springboot.services;

import com.example.springboot.dto.TicketDto;
import com.example.springboot.models.Ticket;

import java.util.List;

public interface ITicketService extends GenericService<Ticket, Long> {

    TicketDto createTicket(TicketDto dto) ;

    TicketDto assignTicketToUser(Long ticketId, Long userId);

    List<TicketDto> getAllTickets(Long userId);

    TicketDto updateTicket(Long ticketId, TicketDto updatedTicket);
}
