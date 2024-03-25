package com.example.springboot;

import com.example.springboot.controllers.TicketController;
import com.example.springboot.models.Ticket;
import com.example.springboot.models.TicketStatus;
import com.example.springboot.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

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

        when(ticketService.getAllTickets()).thenReturn(expectedTickets);

        // Act
        @SuppressWarnings("unchecked")
        ResponseEntity<List<Ticket>> responseEntity = (ResponseEntity<List<Ticket>>) ticketController.getAllTickets();
        List<Ticket> actualTickets = responseEntity.getBody();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedTickets, actualTickets);
        verify(ticketService, times(1)).getAllTickets();
    }


    @Test
    public void getTicketById_NonExistingTicketId_ReturnsNotFound() {
        // Arrange
        Long ticketId = 1L;
        when(ticketService.getTicketById(ticketId)).thenThrow(new IllegalArgumentException("Ticket not found"));

        // Act
        Ticket responseEntity = ticketController.getTicketById(ticketId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatus());
        verify(ticketService, times(1)).getTicketById(ticketId);
    }

    // Similar tests for other methods can be added here
}