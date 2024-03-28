package com.example.springboot.models;

import lombok.*;

import javax.persistence.*;

import com.example.springboot.dto.UserDto;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @OneToMany(mappedBy = "assignedUser")
    private List<Ticket> assignedTickets;

    public UserDto userDto() {
        return new UserDto(id, username, email, assignedTickets);
    }

}
