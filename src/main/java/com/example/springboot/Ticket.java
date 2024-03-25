package com.example.springboot;
import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    // Constructeurs, getters et setters

    // Autres méthodes spécifiques à la classe Ticket
}