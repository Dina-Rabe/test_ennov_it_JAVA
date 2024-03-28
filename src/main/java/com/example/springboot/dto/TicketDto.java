package com.example.springboot.dto;

import com.example.springboot.models.Ticket;
import com.example.springboot.models.TicketStatus;
import com.example.springboot.models.User;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    @Nullable
    private Long id;

    @Nonnull
    private String title;

    @Nonnull
    private String description;

    @Nonnull
    private TicketStatus status;

    @Nullable
    private User assignedUser;

    public Ticket toEntity() {
        return Ticket.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .status(this.status)
                .assignedUser(this.assignedUser)
                .build();
    }
}
