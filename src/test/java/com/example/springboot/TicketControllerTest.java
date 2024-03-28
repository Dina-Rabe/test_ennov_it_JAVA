package com.example.springboot;

import com.example.springboot.controllers.TicketController;
import com.example.springboot.dto.TicketDto;
import com.example.springboot.services.ITicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TicketControllerTest {

    @Mock
    private ITicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

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

        when(ticketService.getAllTickets(anyLong())).then(invocation -> expectedTickets);

        List<TicketDto> responseEntity = ticketController.fetchAll(dummyUserId);

        assertEquals(expectedTickets, responseEntity);
    }


    @Test
    public void getTicketById_NonExistingTicketId_ReturnsNotFound() {
        // Arrange
        Long ticketId = 1L;
        when(ticketService.findById(anyLong())).thenThrow(new IllegalArgumentException("Ticket not found"));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketController.getTicketById(ticketId);
        });

        // You can also check the exception's message if necessary
        assertEquals("Ticket not found", exception.getMessage());

        // Assert that the method was called once
        verify(ticketService, times(1)).findById(ticketId);
    }


}
