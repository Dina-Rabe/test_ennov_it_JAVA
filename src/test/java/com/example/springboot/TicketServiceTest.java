package com.example.springboot;
import com.example.springboot.dto.TicketDto;
import com.example.springboot.models.Ticket;
import com.example.springboot.repositories.TicketRepository;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.ITicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ITicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTickets_ReturnsListOfTickets() {
        Long dummyUserId = 1L;
        // Arrange
        TicketDto ticket1 = TicketDto.builder().title("title 1").description("Desc 1").build();
        TicketDto ticket2 = TicketDto.builder().title("title 1").description("Desc 1").build();
        List<TicketDto> expectedTickets = Arrays.asList(ticket1, ticket2);

        when(ticketService.getAllTickets(anyLong())).thenReturn(expectedTickets);

        List<TicketDto> allTickets = ticketService.getAllTickets(dummyUserId);
        // Assert
        assertEquals(expectedTickets, allTickets);
        verify(ticketService, times(1)).getAllTickets(anyLong());
    }

    @Test
    public void getTicketById_ExistingTicketId_ReturnsTicket() {
        // Arrange
        Long ticketId = 1L;
        Ticket ticket = Ticket.builder().title("title 1").description("Desc 1").build();
        when(ticketService.findById(anyLong())).thenReturn(Optional.of(ticket));

        // Act
        Optional<Ticket> ticketOptional = ticketService.findById(ticketId);

        // Assert
        assertTrue(ticketOptional.isPresent());
        assertEquals(ticket, ticketOptional.get());
        verify(ticketService, times(1)).findById(ticketId);
    }

    @Test
    public void getTicketById_NonExistingTicketId_ThrowsException() {
        // Arrange
        Long ticketId = 1L;
        when(ticketService.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> ticketService.findById(ticketId));
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    
}
