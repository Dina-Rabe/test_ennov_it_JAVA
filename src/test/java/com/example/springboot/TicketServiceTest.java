package com.example.springboot;
import com.example.springboot.models.Ticket;
import com.example.springboot.models.TicketStatus;
import com.example.springboot.repositories.TicketRepository;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.TicketService;

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
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTickets_ReturnsListOfTickets() {
        // Arrange
        Ticket ticket1 = new Ticket("Title 1", "Description 1", null, TicketStatus.OUVERT);
        Ticket ticket2 = new Ticket("Title 2", "Description 2", null, TicketStatus.OUVERT);
        List<Ticket> expectedTickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(expectedTickets);

        // Act
        List<Ticket> actualTickets = ticketService.getAllTickets();

        // Assert
        assertEquals(expectedTickets, actualTickets);
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void getTicketById_ExistingTicketId_ReturnsTicket() {
        // Arrange
        Long ticketId = 1L;
        Ticket expectedTicket = new Ticket("Title 3", "Description 3", null, TicketStatus.OUVERT);
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(expectedTicket));

        // Act
        Ticket actualTicket = ticketService.getTicketById(ticketId);

        // Assert
        assertEquals(expectedTicket, actualTicket);
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void getTicketById_NonExistingTicketId_ThrowsException() {
        // Arrange
        Long ticketId = 1L;
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> ticketService.getTicketById(ticketId));
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    // Similar tests for other methods can be added here
}