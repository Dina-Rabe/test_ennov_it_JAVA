package com.example.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Ajoutez les méthodes spécifiques si nécessaire
}