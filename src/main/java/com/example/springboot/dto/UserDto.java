package com.example.springboot.dto;

import java.util.List;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.User;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Nullable
    private Long id;

    @Nonnull
    private String username;
    
    @Nonnull
    private String email;

    @Nullable
    private List<Ticket> assignedTickets;

    public User toEntity(){
        return User.builder()
                        .id(this.id)
                        .username(this.username)
                        .email(this.email)
                        .assignedTickets(this.assignedTickets)
                        .build();
    }

}
