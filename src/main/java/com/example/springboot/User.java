package com.example.springboot;
import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @OneToMany(mappedBy = "assignedUser")
    private List<Ticket> assignedTickets;

    // Constructeurs, getters et setters

    // Autres méthodes spécifiques à la classe User
}