package com.example.springboot.repositories;
import com.example.springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends GenericRepository<Ticket, Long> {

    List<Ticket> findAllByAssignedUser(User user);
}
